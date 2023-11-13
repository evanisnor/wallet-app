package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.data.Card
import com.evanisnor.walletapp.wallet.data.Wallet
import java.time.LocalDate

/**
 * Shared data for Wallet tests
 */
object WalletViewTestData {

  val creditCardA = Card.CreditCard(
    number = "214398649384",
    balance = 143.22,
    limit = 5000.0,
    expiresOn = LocalDate.of(2025, 3, 22),
    nextStatementOn = LocalDate.of(2023, 11, 30),
  )

  val creditCardB = Card.CreditCard(
    number = "983787560921",
    balance = 3245.43,
    limit = 7000.0,
    expiresOn = LocalDate.of(2025, 8, 16),
    nextStatementOn = LocalDate.of(2023, 11, 30),
  )

  val wallet = Wallet(
    ownerName = "James",
    cashAmount = 553.82,
    cards = listOf(
      creditCardA,
      Card.GiftCard(
        vendorName = "Bamazone.com",
        amount = 50.0,
      )
    )
  )

}