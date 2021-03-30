/*
 * Copyright (c) 2018, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ballerinalang.langserver.extensions.ballerina.symbol;

import io.ballerina.compiler.api.SemanticModel;
import io.ballerina.compiler.api.impl.symbols.BallerinaUnionTypeSymbol;
import io.ballerina.compiler.api.symbols.*;
import io.ballerina.tools.text.LinePosition;
import org.ballerinalang.langserver.LSClientLogger;
import org.ballerinalang.langserver.common.utils.CommonUtil;
import org.ballerinalang.langserver.commons.LanguageServerContext;
import org.ballerinalang.langserver.commons.workspace.WorkspaceManager;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.TextDocumentIdentifier;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Ballerina Symbol Service LS Extension.
 *
 * @since 0.981.2
 */
public class BallerinaSymbolServiceImpl implements BallerinaSymbolService {
    private final WorkspaceManager workspaceManager;
    private final LSClientLogger clientLogger;
    private final LanguageServerContext serverContext;

    public BallerinaSymbolServiceImpl(WorkspaceManager workspaceManager, LanguageServerContext serverContext) {
        this.workspaceManager = workspaceManager;
        this.clientLogger = LSClientLogger.getInstance(serverContext);
        this.serverContext = serverContext;
    }

    @Override
    public CompletableFuture<BallerinaEndpointsResponse> endpoints() {
        return CompletableFuture.supplyAsync(() -> {
            BallerinaEndpointsResponse response = new BallerinaEndpointsResponse();
            response.setEndpoints(getClientEndpoints());
            return response;
        });
    }

    private List<Endpoint> getClientEndpoints() {
        final List<Endpoint> endpoints = new ArrayList<>();
        // TODO: Implementation Required
        return endpoints;
    }

    @Override
    public CompletableFuture<ExpressionTypeResponse> type(ExpressionTypeRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            ExpressionTypeResponse expressionTypeResponse = new ExpressionTypeResponse();
            String fileUri = request.getDocumentIdentifier().getUri();
            Optional<Path> filePath = CommonUtil.getPathFromURI(fileUri);
            if (!filePath.isPresent()) {
                return expressionTypeResponse;
            }

            try {
                expressionTypeResponse.setDocumentIdentifier(new TextDocumentIdentifier(fileUri));

                // Get the semantic model.
                Optional<SemanticModel> semanticModel = this.workspaceManager.semanticModel(filePath.get());

                if (semanticModel.isPresent()) {
                    Optional<Symbol> symbol = Optional.empty();
                    for (int i = 0; i < request.getPosition().offset(); i++) {
                        symbol = semanticModel.get().symbol(workspaceManager.document(filePath.get()).get(), LinePosition.from(request.getPosition().line(), request.getPosition().offset() - i));
                        if (symbol.isPresent()) {
                            break;
                        }
                    }
                    if (symbol.isPresent()) {
                        expressionTypeResponse.setTypes(getAllUnionTypes(symbol.get()));
                        return expressionTypeResponse;
                    }
                }
                return expressionTypeResponse;
            } catch (Throwable e) {
                String msg = "Operation 'ballerinaSymbol/type' failed!";
                this.clientLogger.logError(msg, e, request.getDocumentIdentifier(),
                        (Position) null);
                return expressionTypeResponse;
            }
        });
    }

    private List<String> getAllUnionTypes(Symbol symbol) {
        List<String> allTypes = new ArrayList<>();
        if (symbol.kind() == SymbolKind.VARIABLE) {
            VariableSymbol variableSymbol = (VariableSymbol) symbol;
            if (variableSymbol.typeDescriptor().typeKind() == TypeDescKind.UNION) {
                BallerinaUnionTypeSymbol ballerinaUnionTypeSymbol = (BallerinaUnionTypeSymbol) variableSymbol.typeDescriptor();
                for (TypeSymbol typeSymbol: ballerinaUnionTypeSymbol.memberTypeDescriptors()) {
                    if (!allTypes.contains(typeSymbol.typeKind().getName()))
                        allTypes.add(typeSymbol.typeKind().getName());
                }
            } else if (variableSymbol.typeDescriptor().typeKind() == TypeDescKind.TYPE_REFERENCE) {
                allTypes.add(variableSymbol.typeDescriptor().getName().get());
            } else {
                allTypes.add(variableSymbol.typeDescriptor().typeKind().getName());
            }
        } else if (symbol.kind() == SymbolKind.METHOD) {
            MethodSymbol methodSymbol = (MethodSymbol) symbol;
            TypeSymbol returnTypeSymbol = methodSymbol.typeDescriptor().returnTypeDescriptor().get();
            if (returnTypeSymbol.typeKind() == TypeDescKind.UNION) {
                BallerinaUnionTypeSymbol ballerinaUnionTypeSymbol = (BallerinaUnionTypeSymbol) returnTypeSymbol;
                for (TypeSymbol typeSymbol: ballerinaUnionTypeSymbol.memberTypeDescriptors()) {
                    if (!allTypes.contains(typeSymbol.typeKind().getName()))
                        allTypes.add(typeSymbol.typeKind().getName());
                }
            } else {
                allTypes.add(returnTypeSymbol.typeKind().getName());
            }
        }
        
        return allTypes;
    }
}
