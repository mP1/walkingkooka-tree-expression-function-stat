/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
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

import walkingkooka.Cast;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;
import java.util.Objects;

/**
 * Sums all the parameters after converting them to a number. Null values are skipped.
 */
final class NumberExpressionFunctionSum<C extends ExpressionEvaluationContext> extends NumberExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> NumberExpressionFunctionSum<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NumberExpressionFunctionSum<?> INSTANCE = new NumberExpressionFunctionSum<>();

    private NumberExpressionFunctionSum() {
        super("sum");
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    @Override
    public ExpressionNumber apply(final List<Object> parameters,
                                  final C context) {
        return NUMBERS.getVariable(parameters, 0)
            .stream()
            .filter(Objects::nonNull)
            .map(p -> (ExpressionNumber) p)
            .reduce(
                context.expressionNumberKind()
                    .zero(),
                (subTotal, p) -> subTotal.add(p, context)
            );
    }
}
