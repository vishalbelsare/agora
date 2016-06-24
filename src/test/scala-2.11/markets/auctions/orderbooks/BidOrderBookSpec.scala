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
package markets.auctions.orderbooks

import markets.MarketsTestKit
import markets.orders.BidOrder
import markets.tradables.Tradable
import org.scalatest.{FeatureSpecLike, Matchers}

import scala.util.Random


class BidOrderBookSpec extends OrderBookSpec[BidOrder]("OrderBook[BidOrder]")
  with FeatureSpecLike
  with Matchers
  with MarketsTestKit {

  def prng: Random = new Random(4)

  def orderBookFactory(tradable: Tradable) = OrderBook[BidOrder](validTradable)

  /** Generate a random `Order`.
    *
    * @param marketOrderProbability probability of generating a `MarketOrder`.
    * @param minimumPrice lower bound on the price for a `LimitOrder`.
    * @param maximumPrice upper bound on the price for a `LimitOrder`.
    * @param minimumQuantity lower bound on the `Order` quantity.
    * @param maximumQuantity upper bound on the `Order` quantity.
    * @param timestamp a timestamp for the `Order`.
    * @param tradable the `Order` validTradable.
    * @return either `LimitOrder` or `MarketOrder`, depending.
    */
  def generateRandomOrder(marketOrderProbability: Double,
                          minimumPrice: Long,
                          maximumPrice: Long,
                          minimumQuantity: Long,
                          maximumQuantity: Long,
                          timestamp: Long,
                          tradable: Tradable): BidOrder = {
    randomBidOrder(marketOrderProbability, minimumPrice, maximumPrice, minimumQuantity,
      maximumQuantity, timestamp, tradable)
  }

}