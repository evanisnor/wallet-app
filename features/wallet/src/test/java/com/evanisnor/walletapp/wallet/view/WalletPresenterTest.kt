package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.data.ExampleWalletRepository
import com.google.common.truth.Truth
import org.junit.Test

/**
 * Tests for [WalletPresenter]
 */
class WalletPresenterTest {

  private val underTest = WalletPresenter(
    walletRepository = ExampleWalletRepository()
  )

  @Test
  fun `present - when any user is logged in - returns wallet state`() {
    val walletState = underTest.present()

    Truth.assertThat(walletState).isEqualTo(
      WalletState(
        greeting = "Hello, James!",
        cashAmount = 553.82,
        netBalance = 410.6,
        amountOwed = 143.22,
        totalCredit = 5000.0,
        creditCards = listOf(
          WalletState.CreditCard(
            issuer = "WunderCard",
            numberRedacted = "2143********",
            balance = 143.22,
            isPaymentDueSoon = false,
          )
        ),
        giftCards = listOf(
          WalletState.GiftCard(
            vendorName = "Bamazone.com",
            amount = 50.0,
            isHidden = false,
          )
        ),
      )
    )
  }
}