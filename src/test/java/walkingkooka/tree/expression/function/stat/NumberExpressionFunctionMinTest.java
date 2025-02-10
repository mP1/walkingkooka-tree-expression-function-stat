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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NumberExpressionFunctionMinTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionMin<ExpressionEvaluationContext>> {

    @Test
    public void testApplyZeroParameters() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createBiFunction()
                .apply(
                    Lists.empty(),
                    this.createContext()
                )
        );
    }

    @Test
    public void testApplyOneParameters() {
        this.applyAndCheck2(
            Lists.of(1),
            KIND.create(1)
        );
    }

    @Test
    public void testApplyTwoParameters() {
        this.applyAndCheck2(
            Lists.of(
                -10,
                20
            ),
            KIND.create(-10)
        );
    }

    @Test
    public void testApplyManyParameters() {
        this.applyAndCheck2(
            Lists.of(
                99,
                5,
                10,
                20
            ),
            KIND.create(5)
        );
    }

    @Test
    public void testApplyManyParameters2() {
        this.applyAndCheck2(
            Lists.of(
                -99,
                -5.5,
                -10,
                -20
            ),
            KIND.create(-99)
        );
    }

    @Test
    public void testApplyMixedNumberTypes() {
        this.applyAndCheck2(
            List.of(
                (byte) 1,
                (short) 2,
                3,
                4L,
                BigInteger.valueOf(5),
                BigDecimal.valueOf(6),
                KIND.create(7),
                8.5
            ),
            KIND.create(1)
        );
    }

    @Test
    public void testApplySkipNulls() {
        this.applyAndCheck2(
            Lists.of(
                null,
                10,
                20,
                5
            ),
            KIND.create(5)
        );
    }

    @Override
    public NumberExpressionFunctionMin<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionMin.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }
    
    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "min");
    }

    // class.............................................................................................................
    
    @Override
    public Class<NumberExpressionFunctionMin<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionMin.class);
    }
}
