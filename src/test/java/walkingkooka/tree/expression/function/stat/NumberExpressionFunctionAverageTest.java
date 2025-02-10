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
import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionEvaluationException;
import walkingkooka.tree.expression.ExpressionNumberKind;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NumberExpressionFunctionAverageTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionAverage<ExpressionEvaluationContext>> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testZeroParameters() {
        this.applyAndCheck2(
            Lists.of(1),
            KIND.one()
        );
    }

    @Test
    public void testTenParameters() {
        this.applyAndCheck2(Collections.nCopies(10, 20), KIND.create(20));
    }

    @Test
    public void testSkipsNull() {
        this.applyAndCheck2(
            Lists.of(
                null,
                1,
                20,
                null,
                300
            ),
            KIND.create(
                (1 + 20 + 300) / 3
            )
        );
    }

    @Test
    public void testOnlyNulls() {
        final ExpressionEvaluationException thrown = assertThrows(
            ExpressionEvaluationException.class,
            () -> this.apply2(
                null,
                null
            )
        );

        this.checkEquals(
            "Division by zero",
            thrown.getMessage()
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "average");
    }

    @Override
    public NumberExpressionFunctionAverage<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionAverage.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<NumberExpressionFunctionAverage<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionAverage.class);
    }
}
