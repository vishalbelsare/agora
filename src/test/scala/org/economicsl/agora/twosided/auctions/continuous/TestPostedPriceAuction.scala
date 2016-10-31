/*
Copyright 2016 ScalABM

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.economicsl.agora.twosided.auctions.continuous

import java.util.UUID

import org.economicsl.agora.onesided.auctions.{TestBuyerPostedPriceAuction, TestSellerPostedPriceAuction}
import org.economicsl.agora.tradables.orders.ask.LimitAskOrder
import org.economicsl.agora.tradables.orders.bid.LimitBidOrder
import org.economicsl.agora.tradables.Tradable
import org.economicsl.agora.{orderbooks, Fill}

import scala.collection.mutable


/** Class used to test a two-sided, continuous `PostedPriceAuction`. */
case class TestPostedPriceAuction(tradable: Tradable)(implicit askOrdering: Ordering[LimitAskOrder], bidOrdering: Ordering[LimitBidOrder])
  extends PostedPriceAuction[LimitAskOrder, orderbooks.mutable.SortedOrderBook[LimitAskOrder, mutable.Map[UUID, LimitAskOrder]], LimitBidOrder, orderbooks.mutable.SortedOrderBook[LimitBidOrder, mutable.Map[UUID, LimitBidOrder]]] {

  def fill(order: LimitAskOrder): Option[Fill] = buyerPostedPriceAuction.fill(order) match {
    case result @ Some(fill) => result
    case None => place(order); None  // SIDE EFFECT!
  }

  def fill(order: LimitBidOrder): Option[Fill] = sellerPostedPriceAuction.fill(order) match {
    case result @ Some(fill) => result
    case None => place(order); None  // SIDE EFFECT!
  }

  protected val buyerPostedPriceAuction = TestBuyerPostedPriceAuction(tradable)(bidOrdering)

  protected  val sellerPostedPriceAuction = TestSellerPostedPriceAuction(tradable)(askOrdering)

}