import { colors } from "@mui/material";

import { BarChart } from '@mui/x-charts/BarChart';
import {PieChart } from '@mui/x-charts/PieChart';
import {useEffect, useState} from 'react';
import CategoryTransactions from "../components/statistics/CategoryTransactions";
import UserTransactionCategories from "../components/statistics/UserTransactionCategories";
import Page from "../components/Page";
import TotalBalanceAcrossAccounts from "../components/statistics/TotalBalanceAcrossAccounts";



const StatisticsPage = () => {



    return (<Page>
        
        <CategoryTransactions/>
        <UserTransactionCategories/>
        <TotalBalanceAcrossAccounts/>

    

        
    </Page>);
}

export default StatisticsPage;