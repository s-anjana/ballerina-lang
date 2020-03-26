/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerinalang.compiler.parser.test.incremental;

import io.ballerinalang.compiler.syntax.tree.Node;
import io.ballerinalang.compiler.syntax.tree.SyntaxTree;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Contains module level declarations incremental parsing tests.
 *
 * @since 1.3.0
 */
public class ModuleLevelDeclarationTest {

    @Test
    public void testVariableNameChange() {
        SyntaxTree oldTree = TestUtils.parse("module_declarations/function_name_old.bal");
        SyntaxTree newTree = TestUtils.parse(oldTree, "module_declarations/function_name_new.bal");
        Node[] newNodes = TestUtils.populateNewNodes(oldTree, newTree);

        // TODO This is fragile way to test. Improve
        Assert.assertEquals(newNodes.length, 7);
    }
}