package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.data.Card
import com.evanisnor.walletapp.wallet.data.Wallet
import java.time.LocalDate

/**
 * Shared data for Wallet tests
 */
object WalletViewTestData {

  val creditCardA = Card.CreditCard(
    numberRedacted = "2143********",
    issuer = "WunderCard",
    balance = 143.22,
    limit = 5000.0,
    expiresOn = LocalDate.of(2025, 3, 22),
    nextStatementOn = LocalDate.of(2023, 11, 30),
  )

  val creditCardB = Card.CreditCard(
    numberRedacted = "9837********",
    issuer = "CorpBank of America XTra Cash",
    balance = 3245.43,
    limit = 7000.0,
    expiresOn = LocalDate.of(2025, 8, 16),
    nextStatementOn = LocalDate.of(2023, 11, 30),
  )

  val giftCard = Card.GiftCard(
    vendorName = "Bamazone.com",
    amount = 50.0,
  )

  val wallet = Wallet(
    ownerName = "James",
    cashAmount = 553.82,
    cards = listOf(
      creditCardA,
      giftCard,
    )
  )

}