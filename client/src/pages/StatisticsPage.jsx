import { colors } from "@mui/material";

import { BarChart } from '@mui/x-charts/BarChart';
import {PieChart } from '@mui/x-charts/PieChart';
import {useEffect, useState} from 'react';
import CategoryTransactions from "../components/statistics/CategoryTransactions";
import UserTransactionCategories from "../components/statistics/UserTransactionCategories";
import Page from "../components/Page";
import TotalBalanceAcrossAccounts from "../components/statistics/TotalBalanceAcrossAccounts";
import AmountSpentByCategoryForAccount from "../components/statistics/AmountSpentByCategoryForAccount";
import BankLoansPerQuarter from "../components/statistics/BankLoansPerQuarter";
import TotalDepositsPerQuarter from "../components/statistics/TotalDepositsPerQuarter";
import TotalWithdrawsPerQuarter from "../components/statistics/TotalWithdrawsPerQuarter";



const StatisticsPage = () => {



    return (<Page>
  <div className="container mt-4">
    <div className="row">
      <div className="col-md-6 mb-4">
        <CategoryTransactions />
      </div>
      <div className="col-md-6 mb-4">
        <BankLoansPerQuarter />
      </div>
      <div className="col-md-6 mb-4">
        <TotalDepositsPerQuarter />
      </div>
      <div className="col-md-6 mb-4">
        <TotalWithdrawsPerQuarter />
      </div>
    </div>
  </div>
</Page>
);
}

export default StatisticsPage;