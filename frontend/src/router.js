
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import NoticeManager from "./components/listers/NoticeCards"
import NoticeDetail from "./components/listers/NoticeDetail"

import MemberManager from "./components/listers/MemberCards"
import MemberDetail from "./components/listers/MemberDetail"

import PaymentManager from "./components/listers/PaymentCards"
import PaymentDetail from "./components/listers/PaymentDetail"

import ReservationManager from "./components/listers/ReservationCards"
import ReservationDetail from "./components/listers/ReservationDetail"
import BiddingManager from "./components/listers/BiddingCards"
import BiddingDetail from "./components/listers/BiddingDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/notices',
                name: 'NoticeManager',
                component: NoticeManager
            },
            {
                path: '/notices/:id',
                name: 'NoticeDetail',
                component: NoticeDetail
            },

            {
                path: '/members',
                name: 'MemberManager',
                component: MemberManager
            },
            {
                path: '/members/:id',
                name: 'MemberDetail',
                component: MemberDetail
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },
            {
                path: '/payments/:id',
                name: 'PaymentDetail',
                component: PaymentDetail
            },

            {
                path: '/reservations',
                name: 'ReservationManager',
                component: ReservationManager
            },
            {
                path: '/reservations/:id',
                name: 'ReservationDetail',
                component: ReservationDetail
            },
            {
                path: '/biddings',
                name: 'BiddingManager',
                component: BiddingManager
            },
            {
                path: '/biddings/:id',
                name: 'BiddingDetail',
                component: BiddingDetail
            },



    ]
})
