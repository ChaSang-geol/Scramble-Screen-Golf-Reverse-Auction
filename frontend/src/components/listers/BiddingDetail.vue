<template>
    <v-card outlined>
        <v-card-title>
            Bidding # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="BiddingId" v-model="item.biddingId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="GameReservationId" v-model="item.gameReservationId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BusinessId" v-model="item.businessId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BusinessName" v-model="item.businessName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BusinessPhoneNum" v-model="item.businessPhoneNum" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="BiddingAmount" v-model="item.biddingAmount" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BiddingStatus" v-model="item.biddingStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'BiddingDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/biddings/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
        },
    };
</script>
