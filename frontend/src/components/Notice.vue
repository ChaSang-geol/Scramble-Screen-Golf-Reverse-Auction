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
            Notice # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
        </v-card-title >
        <v-card-title v-else>
            Notice
        </v-card-title >

        <v-card-text>
            <div>
                <Number label="NotiId" v-model="value.notiId" :editMode="editMode"/>
            </div>
            <div>
                <String label="ReceiverPhoneNum" v-model="value.receiverPhoneNum" :editMode="editMode"/>
            </div>
            <div>
                <String label="Content" v-model="value.content" :editMode="editMode"/>
            </div>
            <div>
                <String label="SenderPhoneNum" v-model="value.senderPhoneNum" :editMode="editMode"/>
            </div>
            <div>
                <Date label="SendDate" v-model="value.sendDate" :editMode="editMode"/>
            </div>
            <div>
                <String label="NotiFlag" v-model="value.notiFlag" :editMode="editMode"/>
            </div>
            <div>
                <Number label="GameReservationId" v-model="value.gameReservationId" :editMode="editMode"/>
            </div>
            <div>
                <Number label="BiddingId" v-model="value.biddingId" :editMode="editMode"/>
            </div>
            <div>
                <Number label="Settleid" v-model="value.settleid" :editMode="editMode"/>
            </div>
            <div>
                <Number label="PaymentId" v-model="value.paymentId" :editMode="editMode"/>
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
        name: 'Notice',
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
                if(!('notiId' in this.value)) {
                    this.value.notiId = null;
                }
                if(!('receiverPhoneNum' in this.value)) {
                    this.value.receiverPhoneNum = null;
                }
                if(!('content' in this.value)) {
                    this.value.content = null;
                }
                if(!('senderPhoneNum' in this.value)) {
                    this.value.senderPhoneNum = null;
                }
                if(!('sendDate' in this.value)) {
                    this.value.sendDate = null;
                }
                if(!('notiFlag' in this.value)) {
                    this.value.notiFlag = null;
                }
                if(!('gameReservationId' in this.value)) {
                    this.value.gameReservationId = null;
                }
                if(!('biddingId' in this.value)) {
                    this.value.biddingId = null;
                }
                if(!('settleid' in this.value)) {
                    this.value.settleid = null;
                }
                if(!('paymentId' in this.value)) {
                    this.value.paymentId = null;
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
                            temp = await axios.post(axios.fixUrl('/notices'), this.value)
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

