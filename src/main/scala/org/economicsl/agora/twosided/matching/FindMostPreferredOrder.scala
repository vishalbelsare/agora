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

import org.economicsl.agora.tradables.orders.ask.AskOrder
import org.economicsl.agora.tradables.orders.bid.BidOrder
import org.economicsl.agora.tradables.orders.Operator
import org.economicsl.agora.{onesided, orderbooks}


/** Class defining a `MatchingMechanism` that matches an `AskOrder` (`BidOrder`) with its most preferred `BidOrder`
  * (`AskOrder`) in an `OrderBook`.
  *
  * @tparam A the type of `AskOrder` matched by this `MatchingMechanism`.
  * @tparam B the type of `BidOrder` matched by this `MatchingMechanism`.
  * @note `FindFirstAcceptableOrder` is intended for use in two-sided, continuous auctions.
  */
class FindMostPreferredOrder[A <: AskOrder with Operator[B], B <: BidOrder with Operator[A]]
  extends MatchingMechanism[A, orderbooks.OrderBookLike[A], B, orderbooks.OrderBookLike[B]]{

  /** `MatchingFunction` used to match an `AskOrder` with an order book containing `BidOrder` instances. */
  val askOrderMatchingFunction = new onesided.matching.FindMostPreferredOrder[A, B]()

  /** `MatchingFunction` used to match a `BidOrder` with an order book containing `AskOrder` instances. */
  val bidOrderMatchingFunction = new onesided.matching.FindMostPreferredOrder[B, A]()

}


object FindMostPreferredOrder {

  /** Create an instance of a `FindMostPreferredOrder` matching mechanism.
    *
    * @tparam A the type of `AskOrder` matched by this `MatchingMechanism`.
    * @tparam B the type of `BidOrder` matched by this `MatchingMechanism`.
    * @return an instance of a `FindMostPreferredOrder` matching mechanism.
    */
  def apply[A <: AskOrder with Operator[B], B <: BidOrder with Operator[A]](): FindMostPreferredOrder[A, B] = {
    new FindMostPreferredOrder[A, B]()
  }

}
