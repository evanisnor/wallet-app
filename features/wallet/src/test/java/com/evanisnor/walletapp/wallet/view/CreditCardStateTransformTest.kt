package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.view.WalletViewTestData.creditCardA
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate

/**
 * Tests for converting immutable Credit Card data into [CreditCardViewModelData]
 */
class CreditCardStateTransformTest {

  @Test
  fun `buildCreditCardState - when issuer is WunderCard - return issuer WunderCard`() {
    val creditCard = creditCardA.copy(issuer = "WunderCard")

    val result = buildCreditCardState(creditCard).issuer

    assertThat(result).isEqualTo("WunderCard")
  }

  @Test
  fun `buildCreditCardState - when card is provided - return redacted card number`() {
    val creditCard = creditCardA.copy(numberRedacted = "1234********")

    val result = buildCreditCardState(creditCard).numberRedacted

    assertThat(result).isEqualTo("1234********")
  }

  @Test
  fun `buildCreditCardState - when card has zero balance - return zero balance`() {
    val creditCard = creditCardA.copy(balance = 0.0)

    val result = buildCreditCardState(creditCard).balance

    assertThat(result).isEqualTo(0.0)
  }

  @Test
  fun `buildCreditCardState - when card has a balance - return current balance`() {
    val creditCard = creditCardA.copy(balance = 1.0)

    val result = buildCreditCardState(creditCard).balance

    assertThat(result).isEqualTo(1.0)
  }

  @Test
  fun `buildCreditCardState - when payment is due in 7 days - return true for isPaymentDueSoon`() {
    val creditCard = creditCardA.copy(nextStatementOn = LocalDate.now().plusDays(7))

    val result = buildCreditCardState(creditCard).isPaymentDueSoon

    assertThat(result).isTrue()
  }

  @Test
  fun `buildCreditCardState - when payment is due in 8 days - return false for isPaymentDueSoon`() {
    val creditCard = creditCardA.copy(nextStatementOn = LocalDate.now().plusDays(8))

    val result = buildCreditCardState(creditCard).isPaymentDueSoon

    assertThat(result).isFalse()
  }
}
