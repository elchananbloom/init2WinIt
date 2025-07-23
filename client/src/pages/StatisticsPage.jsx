import { colors } from "@mui/material";

import { BarChart } from '@mui/x-charts/BarChart';
import {PieChart } from '@mui/x-charts/PieChart';
import {useEffect, useState} from 'react';
import CategoryTransactions from "../components/statistics/CategoryTransactions";
import UserTransactionCategories from "../components/statistics/UserTransactionCategories";
import Page from "../components/Page";
import TotalBalanceAcrossAccounts from "../components/statistics/TotalBalanceAcrossAccounts";
import AmountSpentByCategoryForAccount from "../components/statistics/AmountSpentByCategoryForAccount";



const StatisticsPage = () => {



    return (<Page>
        
        <CategoryTransactions/>
        <UserTransactionCategories/>
        <TotalBalanceAcrossAccounts/>
        <AmountSpentByCategoryForAccount accountId={1}/>

    

        
    </Page>);
}

export default StatisticsPage;