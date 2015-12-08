/**
 * CSR_Festival_REST
 * Fichier implémenté dans le cadre du cours de CSR et du TP REST en Java.
 *
 * @authors : Nicolas GIGOU & Quentin LAGADEC
 */

$(document).ready(function() {

    /**
     * Ajout de N festivaliers
     * via le formulaire de la page "Ajouter des festivaliers"
     */
    $("#create-user-form button").click( function() {
        var nb_users = new Object();
        nb_users["length"] = parseInt($("#count-add-festiv").val());

        $.ajax({
            type: "post",
            url: "/people",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify(nb_users),
            success: function(data){
                alert("Festivaliers créés.");
            }
        });
    })

    /**
     * Ajout de N bus
     * via le formulaire de la page "Ajouter des bus"
     */
    $("#create-buses-form button").click( function() {
        var nb_buses = new Object();
        nb_buses["nbbus"] = $("#count-add-buses").val();

        $.ajax({
            type: "post",
            url: "/buses",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify(nb_buses),
            success: function(data){
                console.log("SUCCESS. YOU ADD FESTIVALIERS. YOUR PRESENT : "  + data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Ajax error with add users");
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            },
             then: function(data){
                 console.log("SUCCESS. YOU ADD FESTIVALIERS. YOUR PRESENT : "  + data);
             },
        });
    })
    if ( $( "#detail_user_fields" ).length ) {
         var url = $(location).attr('href');
         var res = url.split("/");
         var id = res[4];
         $.ajax({
            type: "get",
            url: "/people/" + id,
            dataType: "json",
            contentType : "application/json",
            success: function(data){
                console.log(data);
                $("#detail_user_id").append(data.id);
                $("#detail_user_nomF").append(data.nom);
                $("#detail_user_prenomF").append(data.prenom);
                $("#detail_user_etatF").append(data.etat);

            }
        });
    }

    /**
     * Liste les festivaliers
     */
    if ( $( "#list_festivaliers_tab" ).length ) {
        var users_table = $('#users-table tbody');
        $.ajax({
            type: "get",
            url: "/people/",
            dataType: "json",
            contentType : "application/json",
            success: function(data){

                $.each(data, function (item) {
                    console.log("TEST");
                    var id = data[item].id;
                    var etatF = data[item].etatF;
                    var url = data[item].url;

                    users_table.append(
                    '<tr>' +
                        '<th><a id="detail_user_' + id +'" href="' + url + '">' + id + '</a></th>' +
                        '<td>' + etatF + '</td>' +
                    '</tr>'
                    );

                    /*$('#detail_user_' + id).click(function() {


                    });*/

                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("Ajax error with list users");
                console.log(jqXHR);
                console.log(textStatus);
            }
        });
    }

});