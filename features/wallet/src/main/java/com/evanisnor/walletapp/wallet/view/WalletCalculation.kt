package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.data.Card
import com.evanisnor.walletapp.wallet.data.Wallet


/**
 * Function interface to enable building single-purpose state calculations from [Wallet] data.
 */
fun interface WalletCalculation {
  operator fun invoke(wallet: Wallet) : Double
}

/**
 * [WalletCalculation] for calculating the amount of money owed on Credit Cards
 */
val amountOwed = WalletCalculation { wallet ->
  wallet.cards
    .filterIsInstance<Card.CreditCard>()
    .map { it.balance }
    .fold(0.0) { acc, balance -> acc + balance }
}

/**
 * [WalletCalculation] for calculating the total credit available by Credit Cards
 */
val totalCredit = WalletCalculation { wallet ->
  wallet.cards
    .filterIsInstance<Card.CreditCard>()
    .map { it.limit }
    .fold(0.0) { acc, limit -> acc + limit }
}
