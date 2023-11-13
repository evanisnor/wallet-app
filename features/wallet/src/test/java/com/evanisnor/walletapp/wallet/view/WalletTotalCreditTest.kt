package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.view.WalletViewTestData.creditCardA
import com.evanisnor.walletapp.wallet.view.WalletViewTestData.creditCardB
import com.evanisnor.walletapp.wallet.view.WalletViewTestData.wallet
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Tests for calculating the amount of money owed on Credit Cards
 */
class WalletTotalCreditTest {

  @Test
  fun `totalCredit - when there are no credit cards in the wallet - return zero` () {
    val wallet = wallet.copy(cards = emptyList())

    val result = totalCredit(wallet)

    assertThat(result).isEqualTo(0.0)
  }

  @Test
  fun `totalCredit - when one card has no limit - return zero` () {
    val wallet = wallet.copy(cards = listOf(
      creditCardA.copy(limit = 0.0),
    ))

    val result = totalCredit(wallet)

    assertThat(result).isEqualTo(0.0)
  }

  @Test
  fun `totalCredit - when one card has a limit - return limit` () {
    val wallet = wallet.copy(cards = listOf(
      creditCardA.copy(limit = 1000.0),
    ))

    val result = totalCredit(wallet)

    assertThat(result).isEqualTo(1000.0)
  }

  @Test
  fun `totalCredit - when two cards have a limit - return total limit` () {
    val wallet = wallet.copy(cards = listOf(
      creditCardA.copy(limit = 1000.0),
      creditCardB.copy(limit = 5000.0),
    ))

    val result = totalCredit(wallet)

    assertThat(result).isEqualTo(6000.0)
  }

}