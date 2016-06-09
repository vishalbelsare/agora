/*
Copyright 2016 David R. Pugh

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
package markets.engines.mutable

import markets.orders.{AskOrder, BidOrder}

import scala.collection.mutable


/** Continuous Double Auction (CDA) Matching Engine. */
class MutableTreeSetCDAMatchingEngine(val askOrdering: Ordering[AskOrder],
                                      val bidOrdering: Ordering[BidOrder],
                                      val initialPrice: Long)
  extends GenericMutableCDAMatchingEngine[mutable.TreeSet[AskOrder], mutable.TreeSet[BidOrder]] {

  var askOrderBook = new MutableTreeSetAskOrderBook()(askOrdering)

  var bidOrderBook = new MutableTreeSetBidOrderBook()(bidOrdering)

}


object MutableTreeSetCDAMatchingEngine {

  def apply(askOrdering: Ordering[AskOrder],
            bidOrdering: Ordering[BidOrder],
            initialPrice: Long): MutableTreeSetCDAMatchingEngine = {
    new MutableTreeSetCDAMatchingEngine(askOrdering, bidOrdering, initialPrice)
  }
  
}