/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
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
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.TypeNameTesting;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.ExpressionPurityTesting;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionKind;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;

import java.util.EnumSet;

public abstract class ExpressionFunctionTestCase<F extends ExpressionFunction<T, ExpressionFunctionContext>, T> implements ExpressionFunctionTesting<F, T, ExpressionFunctionContext>,
        ExpressionPurityTesting,
        TypeNameTesting<F>,
        ClassTesting2<F> {

    ExpressionFunctionTestCase() {
        super();
    }

    @Test
    public final void testIsPureTrue() {
        this.isPureAndCheck(
                this.createBiFunction(),
                new ExpressionPurityContext() {
                    @Override
                    public boolean isPure(final FunctionExpressionName name) {
                        throw new UnsupportedOperationException();
                    }
                },
                true
        );
    }

    @Test
    public final void testKind() {
        this.checkEquals(
                EnumSet.of(
                        ExpressionFunctionKind.REQUIRES_EVALUATED_PARAMETERS,
                        ExpressionFunctionKind.RESOLVE_REFERENCES
                ),
                this.createBiFunction().kinds()
        );
    }

    @Override
    public final JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }

    @Override
    public final String typeNameSuffix() {
        return "";
    }
}
