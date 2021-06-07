import ballerina/jballerina.java;
import ballerina/lang.'value as value;

function workerDeclTest() {
    int a = 20;
    fork {
       @strand{thread:"any"}
	   worker w1 {
	     int x = 0;
	   }
	   @strand{thread:"any"}
	   worker w2 {
	     int y = 0;
	     int g = y + 1;
	   }
	}
    map<any> results = wait {w1, w2};
    worker wy { }
}

function forkWithMessageParsingTest() returns int {
    int x = 5;
    fork {
       @strand{thread:"any"}
	   worker w1 {
	     int a = 5;
	     int b = 0;
	     a -> w2;
	     b = <- w2;
	   }
	   @strand{thread:"any"}
	   worker w2 {
	     int a = 0;
	     int b = 15;
	     a = <- w1;
	     b -> w1;
	   }
	}
    map<any> results = wait {w1, w2};
	return x;
}

function basicForkTest() returns int {
    int x = 10;
    fork {
	   worker w1 {
	     int a = 5;
	     int b = a + 1;
	   }
	   worker w2 {
	     int a = 0;
	     int b = 15;
	   }
	}
    map<any> results = wait {w1, w2};
	return x;
}

function simpleWorkerMessagePassingTest() {
   worker w1 {
     int a = 10;
     a -> w2;
     a = <- w2;
   }
   worker w2 {
     int a = 0;
     int b = 15;
     a = <- w1;
     b -> w1;
   }
}

function forkWithWaitForAny() returns int | error {
    map<any> m = {};
    m["x"] = 25;
    int ret;
    fork {
	   worker w1 {
	     int a = 5;
	     int b = 0;
	     m["x"] = a;
         sleep(1000);
	   }
	   worker w2 {
	     int a = 5;
	     int b = 15;
         sleep(1000);
	     m["x"] = a;
	   }
	   worker w3 {
	     int a = 0;
	     int b = 15;
	     m["x"] = b;
	   }
	}

    () results = wait w1 | w2 | w3;
    return <int>m["x"];
}

function workerReturnTest() returns int {
    worker wx returns int {
	    int x = 50;
	    return x + 1;
    }
    return (wait wx);
}

function workerSameThreadTest() returns any {
    worker w1 returns string {
        return getCurrentThreadName();
    }

    worker w2 returns string {
        return getCurrentThreadName();
    }
    map<string> res = wait {w1, w2};
    res["w"] = getCurrentThreadName();

    return res;
}

function processWithCloneableExpression() returns string {
    worker w1Cloneable returns boolean {
        value:Cloneable w1a = 10;
        value:Cloneable w1b = 11;
        w1a -> w2Cloneable;
        w1b ->> w2Cloneable;
        return true;
    }

    worker w2Cloneable returns boolean {
        value:Cloneable w2a = 15;
        value:Cloneable w2b = 15;
        sleep(10);
        w2a = <- w1Cloneable;
        w2b = <- w1Cloneable;
        if (w2a is int) {
            assertValueEquality(10, w2a);
        } else {
            return false;
        }
        if (w2b is int) {
            assertValueEquality(11, w2b);
        } else {
            return false;
        }
        return true;
    }

    record { boolean w1Cloneable; boolean w2Cloneable; } x = wait {w1Cloneable, w2Cloneable};
    assertValueEquality(true, x.w1Cloneable);
    assertValueEquality(true, x.w2Cloneable);
    return "done";
}

function simpleSyncSendErrorType() returns string {
    @strand {thread: "parent"}
    worker w1Error returns error {
        error w1a = error("10");
        error w1b = error("11");
        w1a -> w2Error;
        w1b ->> w2Error;
        return error("Completed");
    }

    @strand {thread: "parent"}
    worker w2Error returns error {
        error w2a = error("15");
        error w2b = error("15");
        sleep(10);
        w2a = <- w1Error;
        w2b = <- w1Error;
        assertValueEquality("10", w2a.message());
        assertValueEquality("11", w2b.message());
        return error("Completed");
    }

    record {
        error w1Error;
        error w2Error;
    } x = wait {w1Error, w2Error};
    assertValueEquality("Completed", x.w1Error.message());
    assertValueEquality("Completed", x.w2Error.message());
    return "done";
}

function simpleSyncSendXMLType() returns string {
    @strand {thread: "parent"}
    worker w1Xml returns xml {
        xml w1a = xml `10`;
        xml w1b = xml `11`;
        w1a -> w2Xml;
        w1b ->> w2Xml;
        return xml `Completed`;
    }

    @strand {thread: "parent"}
    worker w2Xml returns xml {
        xml w2a = xml `boom`;
        xml w2b = xml `boom`;
        sleep(10);
        w2a = <- w1Xml;
        w2b = <- w1Xml;
        assertValueEquality(xml `10`, w2a);
        assertValueEquality(xml `11`, w2b);
        return xml `Completed`;
    }

    record {
        xml w1Xml;
        xml w2Xml;
    } x = wait {w1Xml, w2Xml};
    assertValueEquality("Completed", x.w1Xml.toString());
    assertValueEquality("Completed", x.w2Xml.toString());
    return "done";
}


function testSimpleSyncSendWithCloneableExpression() {
    assertValueEquality("done", processWithCloneableExpression());
}

type IntRec readonly & record {
                           int ID = 0;
                       };

function testSimpleSyncSendWithReadonlyRecord() {
    assertValueEquality("done", simpleSyncSendReadonllyRecord());
}

function testSimpleSyncSendWithXMLType() {
    assertValueEquality("done", simpleSyncSendXMLType());
}

function testSimpleSyncSendWithErrorType() {
    assertValueEquality("done", simpleSyncSendErrorType());
}

function simpleSyncSendReadonllyRecord() returns string {
    @strand {thread: "parent"}
    worker w1readonlyRec returns string {
        IntRec w1a = {ID: 10};
        IntRec w1b = {ID: 11};
        w1a -> w2readonlyRec;
        w1b ->> w2readonlyRec;
        return "Completed";
    }

    @strand {thread: "parent"}
    worker w2readonlyRec returns string {
        IntRec w2a = {ID: 3};
        IntRec w2b;
        sleep(10);
        w2a = <- w1readonlyRec;
        w2b = <- w1readonlyRec;
        assertValueEquality(10, w2a.ID);
        assertValueEquality(11, w2b.ID);
        return "Completed";
    }

    record {
        string w1readonlyRec;
        string w2readonlyRec;
    } x = wait {w1readonlyRec, w2readonlyRec};
    assertValueEquality("Completed", x.w1readonlyRec);
    assertValueEquality("Completed", x.w2readonlyRec);
    return "done";
}

type AssertionError distinct error;
const ASSERTION_ERROR_REASON = "AssertionError";

function assertValueEquality(anydata expected, anydata actual) {
    if expected == actual {
        return;
    }
    panic error(ASSERTION_ERROR_REASON,
                message = "expected '" + expected.toString() + "', found '" + actual.toString () + "'");
}


function getCurrentThreadName() returns string {
    handle t = currentThread();
    handle tName = getName(t);
    return java:toString(tName) ?: "";
}

function currentThread() returns handle = @java:Method {
    'class: "java.lang.Thread"
} external;

function getName(handle thread) returns handle = @java:Method {
    'class: "java.lang.Thread"
} external;

public function sleep(int millis) = @java:Method {
    'class: "org.ballerinalang.test.utils.interop.Utils"
} external;
