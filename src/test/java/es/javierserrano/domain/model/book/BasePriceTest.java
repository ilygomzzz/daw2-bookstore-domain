package es.javierserrano.domain.model.book;

import es.javierserrano.domain.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BasePriceTest {
    @Nested
    class testConstructor{
        @Test
        @DisplayName("Given BasePrice should return BasePrice")
        void testBasePrice(){
            BigDecimal expectedBasePrice = new BigDecimal(1.00);
            BasePrice actualBasePrice = new BasePrice(new BigDecimal(1.00 ));

            assertEquals(expectedBasePrice,actualBasePrice.getBasePrice());
        }

        @Test
        @DisplayName("Given null Baseprice value should return ValidationException")
        void testBasePriceNull(){
            assertThrows(ValidationException.class, () -> new BasePrice(null));
        }

        @Test
        @DisplayName("Given Baseprice 0 should return ValidationException")
        void testBasePriceZero(){
            assertThrows(ValidationException.class, () -> new BasePrice(new BigDecimal(0)));
        }

        @Test
        @DisplayName("Given Baseprice negative should return ValidationException")
        void testBasePricenNegative(){
            assertThrows(ValidationException.class, () -> new BasePrice(new BigDecimal(-1)));
        }

    }

}