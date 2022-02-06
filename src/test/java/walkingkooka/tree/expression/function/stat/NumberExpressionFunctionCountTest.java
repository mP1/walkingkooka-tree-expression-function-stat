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
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.Collections;

public final class NumberExpressionFunctionCountTest extends NumberExpressionFunctionTestCase<NumberExpressionFunctionCount<ExpressionFunctionContext>> {

    private final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    @Test
    public void testZeroParameters() {
        this.applyAndCheck2(Lists.empty(), KIND.zero());
    }

    @Test
    public void testOneParameters() {
        this.applyAndCheck2(Lists.of(1), KIND.one());
    }

    @Test
    public void testTenParameters() {
        this.applyAndCheck2(Collections.nCopies(10, 99), KIND.create(10));
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "count");
    }

    @Override
    public NumberExpressionFunctionCount<ExpressionFunctionContext> createBiFunction() {
        return NumberExpressionFunctionCount.instance();
    }

    @Override
    public Class<NumberExpressionFunctionCount<ExpressionFunctionContext>> type() {
        return Cast.to(NumberExpressionFunctionCount.class);
    }
}
