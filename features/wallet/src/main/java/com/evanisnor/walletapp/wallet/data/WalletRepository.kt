package com.evanisnor.walletapp.wallet.data

import java.time.LocalDate

/**
 * Repository for loading [Wallet] data
 */
interface WalletRepository {

  /**
   * Load the [Wallet] that belong to a user given their [userId]
   */
  fun loadWallet(userId: String): Wallet
}

/**
 * An example implementation of [WalletRepository] that returns static data.
 */
class ExampleWalletRepository : WalletRepository {

  override fun loadWallet(userId: String): Wallet =
    Wallet(
      ownerName = "James",
      cashAmount = 553.82,
      cards = listOf(
        Card.CreditCard(
          number = "214398649384",
          issuer = "WunderCard",
          balance = 143.22,
          limit = 5000.0,
          expiresOn = LocalDate.of(2025, 3, 22),
          nextStatementOn = LocalDate.of(2023, 11, 30),
        ),
        Card.GiftCard(
          vendorName = "Bamazone.com",
          amount = 50.0,
        )
      )
    )

}