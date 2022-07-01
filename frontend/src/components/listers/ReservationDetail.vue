<template>
    <v-card outlined>
        <v-card-title>
            Reservation # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="GameReservationId" v-model="item.gameReservationId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MemberId" v-model="item.memberId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="BiddingId" v-model="item.biddingId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="MemberCount" v-model="item.memberCount" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="ReservationTime" v-model="item.reservationTime" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Location" v-model="item.location" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MemberName" v-model="item.memberName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MemberPhoneNum" v-model="item.memberPhoneNum" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReservationStatus" v-model="item.reservationStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'ReservationDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/reservations/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
        },
    };
</script>
