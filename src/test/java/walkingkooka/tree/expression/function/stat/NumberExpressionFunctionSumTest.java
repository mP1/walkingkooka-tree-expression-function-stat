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

import java.util.Collections;

public final class NumberExpressionFunctionSumTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionSum<ExpressionEvaluationContext>> {

    @Test
    public void testApplyZeroParameters() {
        this.applyAndCheck2(
            Lists.empty(),
            KIND.zero()
        );
    }

    @Test
    public void testApplyOneParameters() {
        this.applyAndCheck2(
            Lists.of(1),
            KIND.one()
        );
    }

    @Test
    public void testApplyFewParameters() {
        this.applyAndCheck2(
            Lists.of(1, 2, 3.5),
            KIND.create(1 + 2 + 3.5)
        );
    }

    @Test
    public void testApplyTenParameters() {
        this.applyAndCheck2(
            Collections.nCopies(10, 20),
            KIND.create(10 * 20)
        );
    }

    @Test
    public void testApplySkipNulls() {
        this.applyAndCheck2(
            Lists.of(
                11,
                22,
                null,
                33
            ),
            KIND.create(11 + 22 + 33)
        );
    }

    @Override
    public NumberExpressionFunctionSum<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionSum.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "sum");
    }

    // class............................................................................................................

    @Override
    public Class<NumberExpressionFunctionSum<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionSum.class);
    }
}
