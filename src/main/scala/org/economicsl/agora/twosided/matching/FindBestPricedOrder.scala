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
package org.economicsl.agora.twosided.matching

import java.util.UUID

import org.economicsl.agora.tradables.orders.ask.AskOrder
import org.economicsl.agora.tradables.orders.bid.BidOrder
import org.economicsl.agora.tradables.orders.{NonPriceCriteria, PriceCriteria}
import org.economicsl.agora.{onesided, orderbooks}

import scala.collection.mutable


/** Class defining a `MatchingMechanism` that matches an `AskOrder` (`BidOrder`) with the "best" priced `BidOrder`
  * (`AskOrder`) found in an `OrderBook`.
  *
  * @tparam A the type of `AskOrder` matched by this `MatchingMechanism`.
  * @tparam B the type of `BidOrder` matched by this `MatchingMechanism`.
  * @note `FindBestPricedOrder` is intended for use in two-sided, continuous auctions.
  */
class FindBestPricedOrder[A <: AskOrder with PriceCriteria[B] with NonPriceCriteria[B],
                          B <: BidOrder with PriceCriteria[A] with NonPriceCriteria[A]]
  extends MatchingMechanism[A, orderbooks.mutable.SortedOrderBook[A, mutable.Map[UUID, A]],
                            B, orderbooks.mutable.SortedOrderBook[B, mutable.Map[UUID, B]]] {

  /** One-side matching function used to match an `AskOrder` with an order book containing `BidOrder` instances. */
  val askOrderMatchingFunction = new onesided.matching.FindBestPricedOrder[A, B]()

  /** One-side matching function used to match a `BidOrder` with an order book containing `AskOrder` instances. */
  val bidOrderMatchingFunction = new onesided.matching.FindBestPricedOrder[B, A]()

}


object FindBestPricedOrder {

  /** Create an instance of a `FindBestPricedOrder` matching mechanism.
    *
    * @tparam A the type of `AskOrder` matched by this `MatchingMechanism`.
    * @tparam B the type of `BidOrder` matched by this `MatchingMechanism`.
    * @return an instance of a `FindBestPricedOrder` matching mechanism.
    */
  def apply[A <: AskOrder with PriceCriteria[B] with NonPriceCriteria[B],
            B <: BidOrder with PriceCriteria[A] with NonPriceCriteria[A]](): FindBestPricedOrder[A, B] = {
    new FindBestPricedOrder[A, B]()
  }

}