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
package org.economicsl.agora.markets


import java.util.UUID

import org.economicsl.agora.markets.tradables.Tradable
import org.apache.commons.math3.stat
import org.economicsl.agora.markets.tradables.orders.Order


trait TradingRule[+O <: Order] extends ((Tradable) => O) {

  def issuer: UUID

  def observe: PartialFunction[Any, Unit]

  val performanceSummary: stat.descriptive.SummaryStatistics = TradingRule.summaryStatisticsFactory()

}


object TradingRule {

  def descriptiveStatisticsFactory(window: Int): stat.descriptive.DescriptiveStatistics = {
    new stat.descriptive.DescriptiveStatistics(window)
  }

  def summaryStatisticsFactory(): stat.descriptive.SummaryStatistics = {
    new stat.descriptive.SummaryStatistics()
  }

}



