package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;
import walkingkooka.tree.expression.function.stat.StatExpressionFunctions;

import java.math.MathContext;

public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
                1,
                1
        );
    }

    public void testCount() {
        final ExpressionNumberKind kind = ExpressionNumberKind.BIG_DECIMAL;

        assertEquals(
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
