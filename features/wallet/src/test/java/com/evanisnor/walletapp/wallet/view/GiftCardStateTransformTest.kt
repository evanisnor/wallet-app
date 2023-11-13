package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.view.WalletViewTestData.giftCard
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Tests for converting immutable Gift Card data into [WalletState.GiftCard]
 */
class GiftCardStateTransformTest {

  @Test
  fun `buildGiftCardState - when vendor name is provided - return vendor name`() {
    val giftCard = giftCard.copy(vendorName = "Coffee Central")

    val result = buildGiftCardState(giftCard).vendorName

    assertThat(result).isEqualTo("Coffee Central")
  }

  @Test
  fun `buildGiftCardState - when amount is zero - return zero amount`() {
    val giftCard = giftCard.copy(amount = 0.0)

    val result = buildGiftCardState(giftCard).amount

    assertThat(result).isEqualTo(0.0)
  }

  @Test
  fun `buildGiftCardState - when amount is non-zero - return non-zero amount`() {
    val giftCard = giftCard.copy(amount = 1.0)

    val result = buildGiftCardState(giftCard).amount

    assertThat(result).isEqualTo(1.0)
  }

  @Test
  fun `buildGiftCardState - when balance is greater than 1 dollar - isHidden is true`() {
    val giftCard = giftCard.copy(amount = 0.99)

    val result = buildGiftCardState(giftCard).isHidden

    assertThat(result).isTrue()
  }

  @Test
  fun `buildGiftCardState - when balance is 1 dollar - isHidden is false`() {
    val giftCard = giftCard.copy(amount = 1.0)

    val result = buildGiftCardState(giftCard).isHidden

    assertThat(result).isFalse()
  }
}