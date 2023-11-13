package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.view.WalletViewTestData.creditCardA
import com.evanisnor.walletapp.wallet.view.WalletViewTestData.creditCardB
import com.evanisnor.walletapp.wallet.view.WalletViewTestData.wallet
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Tests for calculating the amount of money owed on Credit Cards
 */
class WalletAmountOwedTest {

  @Test
  fun `amountOwed - when there are no credit cards in the wallet - return zero` () {
    val wallet = wallet.copy(cards = emptyList())

    val result = amountOwed(wallet)

    assertThat(result).isEqualTo(0.0)
  }

  @Test
  fun `amountOwed - when one card has no balance - return zero` () {
    val wallet = wallet.copy(cards = listOf(
      creditCardA.copy(balance = 0.0),
    ))

    val result = amountOwed(wallet)

    assertThat(result).isEqualTo(0.0)
  }

  @Test
  fun `amountOwed - when one card has a balance - return amount owed` () {
    val wallet = wallet.copy(cards = listOf(
      creditCardA.copy(balance = 534.43),
    ))

    val result = amountOwed(wallet)

    assertThat(result).isEqualTo(534.43)
  }

  @Test
  fun `amountOwed - when two cards have a balance - return total amount owed` () {
    val wallet = wallet.copy(cards = listOf(
      creditCardA.copy(balance = 534.43),
      creditCardB.copy(balance = 9434.11),
    ))

    val result = amountOwed(wallet)

    assertThat(result).isEqualTo(9968.54)
  }

}