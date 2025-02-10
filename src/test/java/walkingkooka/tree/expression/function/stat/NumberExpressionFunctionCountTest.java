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
import walkingkooka.tree.expression.ExpressionNumberKind;

public final class NumberExpressionFunctionCountTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionCount<ExpressionEvaluationContext>> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testZeroParameters() {
        this.countAndCheck();
    }

    @Test
    public void testNumber() {
        this.countAndCheck(1);
    }

    @Test
    public void testNumberAndString() {
        this.countAndCheck(
            1,
            "abc"
        );
    }

    @Test
    public void testSkipsNull() {
        this.applyAndCheck(
            Lists.of(
                null,
                111,
                null,
                222
            ),
            KIND.create(2)
        );
    }

    private void countAndCheck(final Object... values) {
        this.applyAndCheck(
            Lists.of(values),
            KIND.create(values.length)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "count");
    }

    @Override
    public NumberExpressionFunctionCount<ExpressionEvaluationContext> createBiFunction() {
        return NumberExpressionFunctionCount.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<NumberExpressionFunctionCount<ExpressionEvaluationContext>> type() {
        return Cast.to(NumberExpressionFunctionCount.class);
    }
}
