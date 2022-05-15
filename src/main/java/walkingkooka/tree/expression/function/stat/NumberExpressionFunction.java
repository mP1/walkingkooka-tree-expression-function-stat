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

import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Base for any function that handles and requires numbers.
 */
abstract class NumberExpressionFunction<C extends ExpressionFunctionContext> implements ExpressionFunction<ExpressionNumber, C> {

    /**
     * Package private ctor
     */
    NumberExpressionFunction(final String name) {
        super();
        this.name = FunctionExpressionName.with(name);
    }

    @Override
    public final FunctionExpressionName name() {
        return name;
    }

    private final FunctionExpressionName name;

    final static ExpressionFunctionParameter<?> NUMBERS = ExpressionFunctionParameterName.with("numbers")
            .variable(List.class)
            .setTypeParameters(Lists.of(ExpressionNumber.class));

    final static List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(
            NUMBERS
    );

    @Override
    public final Class<ExpressionNumber> returnType() {
        return ExpressionNumber.class;
    }

    /**
     * All number functions are pure. Does not assume anything about any parameters.
     */
    @Override
    public final boolean isPure(final ExpressionPurityContext context) {
        return true;
    }

    @Override
    public final Set<ExpressionFunctionKind> kinds() {
        return KINDS;
    }

    private final Set<ExpressionFunctionKind> KINDS = EnumSet.of(
            ExpressionFunctionKind.EVALUATE_PARAMETERS,
            ExpressionFunctionKind.RESOLVE_REFERENCES
    );

    @Override
    public final String toString() {
        return this.name().toString();
    }
}
