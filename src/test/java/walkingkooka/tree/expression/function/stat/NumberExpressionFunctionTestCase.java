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
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class NumberExpressionFunctionTestCase<F extends ExpressionFunction<ExpressionNumber, ExpressionEvaluationContext>> extends ExpressionFunctionTestCase<F, ExpressionNumber> {

    final static ExpressionNumberKind KIND = ExpressionNumberKind.DEFAULT;

    NumberExpressionFunctionTestCase() {
        super();
    }

    @Test
    public final void testDoesntConvert() {
        if (!(this instanceof NumberExpressionFunctionCountTest)) {
            assertThrows(
                    ClassCastException.class,
                    () -> {
                        this.createBiFunction()
                                .apply(Lists.of(1), this.createContext());
                    }
            );
        }
    }

    @Override
    public final void applyAndCheck2(final F function,
                                     final List<Object> parameters,
                                     final ExpressionNumber result) {
        this.applyAndCheck2(
                function,
                parameters.stream()
                        .map(i -> KIND.create((Number) i))
                        .collect(Collectors.toList()),
                this.createContext(),
                result
        );
    }

    @Override
    public final ExpressionEvaluationContext createContext() {
        return this.createContext(KIND);
    }

    final ExpressionEvaluationContext createContext(final ExpressionNumberKind kind) {
        return new FakeExpressionEvaluationContext() {
            @Override
            public ExpressionNumberKind expressionNumberKind() {
                return kind;
            }

            @Override
            public MathContext mathContext() {
                return MathContext.DECIMAL128;
            }

            @Override
            public char negativeSign() {
                return '-';
            }

            @Override
            public char positiveSign() {
                return '+';
            }

            @Override
            public <TT> Either<TT, String> convert(final Object value,
                                                   final Class<TT> target) {
                try {
                    final Number number = value instanceof String ?
                            new BigDecimal((String) value) :
                            (Number) value;
                    return this.successfulConversion(
                            target.cast(kind.create(number)),
                            target
                    );
                } catch (final Exception fail) {
                    return this.failConversion(value, target);
                }
            }
        };
    }

    @Override
    public final String typeNamePrefix() {
        return NumberExpressionFunction.class.getSimpleName();
    }
}
