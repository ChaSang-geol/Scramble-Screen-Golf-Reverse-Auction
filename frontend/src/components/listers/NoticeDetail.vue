<template>
    <v-card outlined>
        <v-card-title>
            Notice # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="NotiId" v-model="item.notiId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReceiverPhoneNum" v-model="item.receiverPhoneNum" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Content" v-model="item.content" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="SenderPhoneNum" v-model="item.senderPhoneNum" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="SendDate" v-model="item.sendDate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="NotiFlag" v-model="item.notiFlag" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="GameReservationId" v-model="item.gameReservationId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="BiddingId" v-model="item.biddingId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Settleid" v-model="item.settleid" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="PaymentId" v-model="item.paymentId" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'NoticeDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/notices/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
        },
    };
</script>
