/*
 * Copyright 2022 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.tree.expression.function.stat;

import org.junit.jupiter.api.Test;
import walkingkooka.collect.set.Sets;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.PublicStaticHelperTesting;
import walkingkooka.tree.expression.function.ExpressionFunction;

import java.lang.reflect.Method;
import java.math.MathContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class StatExpressionFunctionsTest implements PublicStaticHelperTesting<StatExpressionFunctions> {

    @Test
    public void testExpressionFunctionProvider() {
        this.checkEquals(
                Arrays.stream(StatExpressionFunctions.class.getDeclaredMethods())
                        .filter(m -> m.getReturnType() == ExpressionFunction.class)
                        .map(Method::getName)
                        .map(n -> {
                                    // JDK BUG cant have a lambda with switch as the body ???
                                    switch (n) {
                                        case "trueFunction":
                                            return "true";
                                        case "falseFunction":
                                            return "false";
                                        case "booleanFunction":
                                            return "boolean";
                                        case "switchFunction":
                                            return "switch";
                                        case "ifFunction":
                                            return "if";
                                        default:
                                            return n;
                                    }
                                }
                        ).collect(Collectors.toCollection(Sets::sorted)),
                StatExpressionFunctions.expressionFunctionProvider()
                        .expressionFunctionInfos()
                        .stream()
                        .map(i -> i.name().value())
                        .collect(Collectors.toCollection(Sets::sorted))
        );
    }

    @Test
    public void testPublicStaticMethodsWithoutMathContextParameter() {
        this.publicStaticMethodParametersTypeCheck(MathContext.class);
    }

    @Override
    public Class<StatExpressionFunctions> type() {
        return StatExpressionFunctions.class;
    }

    @Override
    public boolean canHavePublicTypes(final Method method) {
        return false;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
