/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.ballerinalang.model.elements;

import org.wso2.ballerinalang.compiler.util.Name;
import org.wso2.ballerinalang.compiler.util.Names;
import org.wso2.ballerinalang.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.wso2.ballerinalang.compiler.util.Names.ANNOTATIONS_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.ARRAY_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.BOOLEAN_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.DECIMAL_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.DEFAULT_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.ERROR_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.FLOAT_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.FUTURE_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.INTERNAL_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.INT_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.JAVA_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.MAP_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.OBJECT_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.OBSERVE_INTERNAL_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.OBSERVE_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.QUERY_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.RUNTIME_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.STREAM_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.STRING_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.TABLE_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.TRANSACTION_INTERNAL_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.TRANSACTION_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.TYPEDESC_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.VALUE_VERSION;
import static org.wso2.ballerinalang.compiler.util.Names.XML_VERSION;

/**
 * This represents a specific package and its version.
 *
 * @since 0.94
 */
public class PackageID {

//    public static final PackageID DEFAULT = new PackageID(Names.ANON_ORG, Names.DEFAULT_PACKAGE, DEFAULT_VERSION);
    public static final PackageID DEFAULT = new PackageID(Names.ANON_ORG, Names.DEFAULT_PACKAGE, Names.DEFAULT_MODULE,
        DEFAULT_VERSION);

    // Lang.* Modules IDs

    // lang.__internal module is visible only to the compiler and peer lang.* modules.
//    public static final PackageID INTERNAL = new PackageID(Names.BALLERINA_ORG,
//            Lists.of(Names.LANG, Names.INTERNAL), INTERNAL_VERSION);
    public static final PackageID INTERNAL = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.INTERNAL), Names.DEFAULT_MODULE, INTERNAL_VERSION);

    // Visible Lang modules.
//    public static final PackageID ANNOTATIONS = new PackageID(Names.BALLERINA_ORG,
//            Lists.of(Names.LANG, Names.ANNOTATIONS), ANNOTATIONS_VERSION);
    public static final PackageID ANNOTATIONS = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.ANNOTATIONS), Names.DEFAULT_MODULE, ANNOTATIONS_VERSION);
    public static final PackageID JAVA = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.JAVA), Names.DEFAULT_MODULE, JAVA_VERSION);
    public static final PackageID ARRAY = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.ARRAY), Names.DEFAULT_MODULE, ARRAY_VERSION);
    public static final PackageID DECIMAL = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.DECIMAL), Names.DEFAULT_MODULE, DECIMAL_VERSION);
    public static final PackageID ERROR = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.ERROR), Names.DEFAULT_MODULE, ERROR_VERSION);
    public static final PackageID FLOAT = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.FLOAT), Names.DEFAULT_MODULE, FLOAT_VERSION);
    public static final PackageID FUTURE = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.FUTURE), Names.DEFAULT_MODULE, FUTURE_VERSION);
    public static final PackageID INT = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.INT), Names.DEFAULT_MODULE, INT_VERSION);
    public static final PackageID MAP = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.MAP), Names.DEFAULT_MODULE, MAP_VERSION);
    public static final PackageID OBJECT = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.OBJECT), Names.DEFAULT_MODULE, OBJECT_VERSION);
    public static final PackageID STREAM = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.STREAM), Names.DEFAULT_MODULE, STREAM_VERSION);
    public static final PackageID STRING = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.STRING), Names.DEFAULT_MODULE, STRING_VERSION);
    public static final PackageID TABLE = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.TABLE), Names.DEFAULT_MODULE, TABLE_VERSION);
    public static final PackageID TYPEDESC = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.TYPEDESC), Names.DEFAULT_MODULE, TYPEDESC_VERSION);
    public static final PackageID VALUE = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.VALUE), Names.DEFAULT_MODULE, VALUE_VERSION);
    public static final PackageID XML = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.XML), Names.DEFAULT_MODULE,XML_VERSION);
    public static final PackageID BOOLEAN = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.BOOLEAN), Names.DEFAULT_MODULE, BOOLEAN_VERSION);
    public static final PackageID QUERY = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.QUERY), Names.DEFAULT_MODULE, QUERY_VERSION);
    public static final PackageID RUNTIME = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.RUNTIME), Names.DEFAULT_MODULE, RUNTIME_VERSION);
    public static final PackageID TRANSACTION = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.LANG, Names.TRANSACTION), Names.DEFAULT_MODULE, TRANSACTION_VERSION);
    public static final PackageID TRANSACTION_INTERNAL = new PackageID(Names.BALLERINA_INTERNAL_ORG,
            Lists.of(Names.TRANSACTION), Names.DEFAULT_MODULE, TRANSACTION_INTERNAL_VERSION);
    public static final PackageID OBSERVE_INTERNAL = new PackageID(Names.BALLERINA_INTERNAL_ORG,
            Lists.of(Names.OBSERVE), Names.DEFAULT_MODULE, OBSERVE_INTERNAL_VERSION);
    public static final PackageID OBSERVE = new PackageID(Names.BALLERINA_ORG,
            Lists.of(Names.OBSERVE), Names.DEFAULT_MODULE, OBSERVE_VERSION);

    public Name orgName;
    // The package name
    public Name pkgName;
    // The module name
    public Name name;
    public Name version;

    public final boolean isUnnamed;
    public final Name sourceFileName;

    // Package name comps
    public final List<Name> pkgNameComps;
    // Module name comps
    public final List<Name> nameComps;

    public PackageID(Name orgName, Name pkgName, List<Name> nameComps, Name version) {
        this.orgName = orgName;
        this.pkgName = pkgName;
        this.pkgNameComps = createPkgNameComps(pkgName);
        this.nameComps = nameComps;
        this.name = new Name(
                nameComps.stream()
                        .map(Name::getValue)
                        .collect(Collectors.joining(".")));
        this.version = version;
        isUnnamed = false;
        sourceFileName = null;
    }

    public PackageID(Name orgName, List<Name> pkgNameComps, List<Name> nameComps, Name version) {
        this.orgName = orgName;
        this.pkgName = new Name(
                pkgNameComps.stream()
                        .map(Name::getValue)
                        .collect(Collectors.joining(".")));
        this.pkgNameComps = pkgNameComps;
        this.nameComps = nameComps;
        this.name = new Name(
                nameComps.stream()
                        .map(Name::getValue)
                        .collect(Collectors.joining(".")));
        this.version = version;
        isUnnamed = false;
        sourceFileName = null;
    }

    public PackageID(Name orgName, List<Name> pkgNameComps, Name moduleName, Name version) {
        this.orgName = orgName;
        this.pkgName = new Name(
                pkgNameComps.stream()
                        .map(Name::getValue)
                        .collect(Collectors.joining(".")));
        this.pkgNameComps = pkgNameComps;
        this.nameComps = createModuleNameComps(moduleName);
        this.name = moduleName;
        this.version = version;
        isUnnamed = false;
        sourceFileName = null;
    }

    // TODO: Remove this method.
//    public PackageID(Name orgName, List<Name> nameComps, Name version) {
//        this.orgName = orgName;
//        this.nameComps = nameComps;
//        this.name = new Name(
//                nameComps.stream()
//                        .map(Name::getValue)
//                        .collect(Collectors.joining(".")));
//        this.version = version;
//        isUnnamed = false;
//        sourceFileName = null;
//    }

    public PackageID(Name orgName, Name pkgName, Name name, Name version) {
        this.orgName = orgName;
        this.name = name;
        this.pkgName = pkgName;
        this.pkgNameComps = createPkgNameComps(pkgName);
        this.version = version;
        this.nameComps = createModuleNameComps(name);
        isUnnamed = false;
        sourceFileName = null;
    }

    // TODO: Remove this method
//    public PackageID(Name orgName, Name name, Name version) {
//        this.orgName = orgName;
//        this.name = name;
//        this.version = version;
//        this.nameComps = createNameComps(name);
//        isUnnamed = false;
//        sourceFileName = null;
//    }

    public PackageID(Name orgName, Name pkgName, Name name, Name version, Name sourceFileName) {
        this.orgName = orgName;
        this.pkgName = pkgName;
        this.pkgNameComps = createPkgNameComps(pkgName);
        this.name = name;
        this.version = version;
        this.nameComps = createModuleNameComps(name);
        isUnnamed = false;
        this.sourceFileName = sourceFileName;
    }

    // TODO: Remove this method
//    public PackageID(Name orgName, Name name, Name version, Name sourceFileName) {
//        this.orgName = orgName;
//        this.name = name;
//        this.version = version;
//        this.nameComps = createNameComps(name);
//        isUnnamed = false;
//        this.sourceFileName = sourceFileName;
//    }

    private List<Name> createModuleNameComps(Name name) {
        if (name == Names.DEFAULT_MODULE) {
            return Lists.of(Names.DEFAULT_MODULE);
        }
        return Arrays.stream(name.value.split("\\.")).map(Name::new).collect(Collectors.toList());
    }

    private List<Name> createPkgNameComps(Name name) {
        if (name == Names.DEFAULT_PACKAGE) {
            return Lists.of(Names.DEFAULT_PACKAGE);
        }
        return Arrays.stream(pkgName.value.split("\\.")).map(Name::new).collect(Collectors.toList());
    }

    /**
     * Creates a {@code PackageID} for an unnamed package.
     *
     * @param orgName        organization name
     * @param sourceFileName name of the .bal file
     * @param version        version
     */
    // TODO: Remove this method.
//    public PackageID(Name orgName, String sourceFileName, Name version) {
//        this.orgName = orgName;
//        this.name = Names.DEFAULT_PACKAGE;
//        this.version = version;
//        this.nameComps = Lists.of(Names.DEFAULT_PACKAGE);
//        this.isUnnamed = true;
//        this.sourceFileName = new Name(sourceFileName);
//    }

    /**
     * Creates a {@code PackageID} for an unnamed package.
     *
     * @param sourceFileName name of the .bal file
     */
    public PackageID(String sourceFileName) {
        this.orgName = Names.ANON_ORG;
        this.pkgName = Names.DEFAULT_PACKAGE;
        this.pkgNameComps = new ArrayList<>(1);
        pkgNameComps.add(pkgName);
        this.name = Names.DEFAULT_MODULE;
//        this.name = Names.DEFAULT_PACKAGE;
        this.nameComps = new ArrayList<>(1);
        nameComps.add(name);
        this.isUnnamed = true;
        this.sourceFileName = new Name(sourceFileName);
        this.version = DEFAULT_VERSION;
    }

    public Name getPkgName() {
        return pkgName;
    }

    public Name getPkgNameComp(int index) {
        return pkgNameComps.get(index);
    }

    public List<Name> getPkgNameComps() {
        return pkgNameComps;
    }

    public Name getName() {
        return name;
    }

    public Name getNameComp(int index) {
        return nameComps.get(index);
    }

    public List<Name> getNameComps() {
        return nameComps;
    }

    public Name getPackageVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PackageID other = (PackageID) o;
        boolean samePkg = false;

        if (this.isUnnamed == other.isUnnamed) {
            samePkg = (!this.isUnnamed) || (this.sourceFileName.equals(other.sourceFileName));
        }

        return samePkg && orgName.equals(other.orgName) && pkgName.equals(other.pkgName)
                && name.equals(other.name) && version.equals(other.version);
    }

    @Override
    public int hashCode() {
        int result = orgName != null ? orgName.hashCode() : 0;
        result = 31 * result + (pkgName != null ? pkgName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + version.hashCode();
        return result;
    }

    @Override
    public String toString() {
//        if (Names.DOT.equals(this.name)) {
//            return this.name.value;
//        }
//
//        String org = "";
//        if (this.orgName != null && !this.orgName.equals(Names.ANON_ORG)) {
//            org = this.orgName + Names.ORG_NAME_SEPARATOR.value;
//        }
//
//        if (version.equals(Names.EMPTY)) {
//            return org + this.name.value;
//        }
//
//        return org + this.name + Names.VERSION_SEPARATOR.value + this.version;

        if (Names.DOT.equals(this.pkgName)) {
            return this.pkgName.value;
        }

        String org = "";
        if (this.orgName != null && !this.orgName.equals(Names.ANON_ORG)) {
            org = this.orgName + Names.ORG_NAME_SEPARATOR.value;
        }

        if (version.equals(Names.EMPTY)) {
            return org + this.pkgName.value + Names.MODULE_SEPARATOR + this.name.value;
        }

        return org + this.pkgName.value + Names.MODULE_SEPARATOR + this.name + Names.VERSION_SEPARATOR.value + this.version;
    }

    public Name getOrgName() {
        return orgName;
    }

    public static boolean isLangLibPackageID(PackageID packageID) {

//        if (!packageID.getOrgName().equals(Names.BALLERINA_ORG)) {
//            return false;
//        }
//        return packageID.nameComps.size() > 1 && packageID.nameComps.get(0).equals(Names.LANG) ||
//                packageID.name.equals(Names.JAVA);

        if (!packageID.getOrgName().equals(Names.BALLERINA_ORG)) {
            return false;
        }
        return packageID.pkgNameComps.size() > 1 && packageID.pkgNameComps.get(0).equals(Names.LANG) ||
                packageID.pkgName.equals(Names.JAVA);
//        return packageID.nameComps.size() > 1 && packageID.nameComps.get(0).equals(Names.LANG) ||
//                packageID.name.equals(Names.JAVA);
    }
}
