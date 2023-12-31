package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.data.Card
import com.evanisnor.walletapp.wallet.data.ExampleWalletRepository


/**
 * Presenter for the Wallet view. Calling [present] from the View will return an instance of
 * [WalletState] which can then be rendered by the view.
 */
class WalletPresenter(
  private val walletRepository: ExampleWalletRepository
) {

  /**
   * Present a [WalletState] to the Wallet View.
   */
  fun present(): WalletState {
    val wallet = walletRepository.loadWallet(userId = "user-99")
    val amountOwed = amountOwed(wallet)
    val totalCredit = totalCredit(wallet)

    val netBalance = wallet.cashAmount - amountOwed

    return WalletState(
      greeting = "Hello, ${wallet.ownerName}!",
      cashAmount = wallet.cashAmount,
      netBalance = netBalance,
      amountOwed = amountOwed,
      totalCredit = totalCredit,
      creditCards = wallet.cards.filterIsInstance<Card.CreditCard>().map {
        buildCreditCardState(it)
      },
      giftCards = wallet.cards.filterIsInstance<Card.GiftCard>().map {
        buildGiftCardState(it)
      }

    )

  }

}