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

package walkingkooka.tree.expression.function.stat.sample;

import org.junit.jupiter.api.Assertions;
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.stat.StatExpressionFunctions;

import java.math.MathContext;

public final class Sample {
    public static void main(final String[] args) {
        final ExpressionNumberKind kind = ExpressionNumberKind.BIG_DECIMAL;

        Assertions.assertEquals(
                kind.create(3),
                StatExpressionFunctions.count()
                        .apply(
                                Lists.of(
                                        kind.create(123),
                                        kind.create(456),
                                        kind.create(789)
                                ),
                                new FakeExpressionEvaluationContext() {
                                    @Override
                                    public ExpressionNumberKind expressionNumberKind() {
                                        return kind;
                                    }

                                    @Override
                                    public MathContext mathContext() {
                                        return MathContext.DECIMAL128;
                                    }

                                    @Override
                                    public <TT> Either<TT, String> convert(final Object value,
                                                                           final Class<TT> target) {
                                        try {
                                            return this.successfulConversion(
                                                    (TT) kind.create((Number) value),
                                                    target
                                            );
                                        } catch (final Exception fail) {
                                            return this.failConversion(value, target);
                                        }
                                    }
                                }
                        )
        );
    }
}
