<template>

    <v-card style="width:450px;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="deep-purple"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            Reservation # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
        </v-card-title >
        <v-card-title v-else>
            Reservation
        </v-card-title >

        <v-card-text>
            <div>
                <Number label="GameReservationId" v-model="value.gameReservationId" :editMode="editMode"/>
            </div>
            <div>
                <String label="MemberId" v-model="value.memberId" :editMode="editMode"/>
            </div>
            <div>
                <Number label="BiddingId" v-model="value.biddingId" :editMode="editMode"/>
            </div>
            <div>
                <Number label="MemberCount" v-model="value.memberCount" :editMode="editMode"/>
            </div>
            <div>
                <Date label="ReservationTime" v-model="value.reservationTime" :editMode="editMode"/>
            </div>
            <div>
                <String label="Location" v-model="value.location" :editMode="editMode"/>
            </div>
            <div>
                <String label="MemberName" v-model="value.memberName" :editMode="editMode"/>
            </div>
            <div>
                <String label="MemberPhoneNum" v-model="value.memberPhoneNum" :editMode="editMode"/>
            </div>
            <div>
                <String label="ReservationStatus" v-model="value.reservationStatus" :editMode="editMode"/>
            </div>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
            >
                Cancel
            </v-btn>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>                        
        </v-card-actions>
    </v-card>

</template>

<script>
    const axios = require('axios').default;
    
    export default {
        name: 'Reservation',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean
        },
        data: () => ({
        }),
        created(){
            if(!this.value) {
                this.value = {};
            }

            if(typeof this.value === 'object') {
                if(!('gameReservationId' in this.value)) {
                    this.value.gameReservationId = null;
                }
                if(!('memberId' in this.value)) {
                    this.value.memberId = null;
                }
                if(!('biddingId' in this.value)) {
                    this.value.biddingId = null;
                }
                if(!('memberCount' in this.value)) {
                    this.value.memberCount = null;
                }
                if(!('reservationTime' in this.value)) {
                    this.value.reservationTime = null;
                }
                if(!('location' in this.value)) {
                    this.value.location = null;
                }
                if(!('memberName' in this.value)) {
                    this.value.memberName = null;
                }
                if(!('memberPhoneNum' in this.value)) {
                    this.value.memberPhoneNum = null;
                }
                if(!('reservationStatus' in this.value)) {
                    this.value.reservationStatus = null;
                }
            }            
        },

        methods: {
            selectFile(){
            if(this.editMode == false) {
                return false;
            }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/reservations'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                } catch(e) {
                    alert(e.message)
                }
                location.reload()
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    alert(e.message)
                }
            },
            change(){
                this.$emit('input', this.value);
            },
        },
    }
</script>

