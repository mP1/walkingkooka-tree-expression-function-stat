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

import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.function.Consumer;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class StatExpressionFunctions implements PublicStaticHelper {

    /**
     * Visit all {@link ExpressionFunction functions}.
     */
    public static void visit(final Consumer<ExpressionFunction<?, ?>> consumer) {
        Lists.of(average(),
                count(),
                max(),
                min(),
                sum()
        ).forEach(consumer);
    }


    /**
     * {@see NumberExpressionFunctionAverage}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> average() {
        return NumberExpressionFunctionAverage.instance();
    }

    /**
     * {@see NumberExpressionFunctionCount}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> count() {
        return NumberExpressionFunctionCount.instance();
    }

    /**
     * {@see NumberExpressionFunctionMax}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> max() {
        return NumberExpressionFunctionMax.instance();
    }

    /**
     * {@see NumberExpressionFunctionMin}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> min() {
        return NumberExpressionFunctionMin.instance();
    }

    /**
     * {@see NumberExpressionFunctionSum}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<ExpressionNumber, C> sum() {
        return NumberExpressionFunctionSum.instance();
    }

    /**
     * Stops creation
     */
    private StatExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}
