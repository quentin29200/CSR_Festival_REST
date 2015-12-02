/**
 * CSR_Festival_REST
 * Fichier implémenté dans le cadre du cours de CSR et du TP REST en Java.
 *
 * Ce fichier rajoute des fonctionnalités à l'application comme la génération de
 * champs d'ajout de festivaliers par exemple.
 *
 */

// Clic sur le bouton de génération
$( "#btn_count_field" ).click(function() {

    // Récuperation valeur donnée par l'utilisateur
    var value_cpt = $("#count").val();

    try {
        // Vérification si la valeur est un chiffre
        if( $.isNumeric(value_cpt) || value_cpt < 100 ) {
            console.log("La valeur est un entier. What a good boy !");
            // Remontre la div contenant le formulaire d'ajout de festivalier
            $(".div_form").removeClass("hidden");
            console.log("D");

            // Change le titre du formulaire en fonction du nombre de festivaliers que l'on veut ajouter
            var $titleForm = $("#title_form_count");
            ( value_cpt > 1 ) ? $titleForm.append("Festivaliers ajoutés") : $titleForm.append("Festivalier ajouté");

            // les champs du formualaire
            var $fieldsForm = $("#create-festivalier-form");

            // Clone le formulaire autant de fois que de value_cpt
            for (i = 0; i < value_cpt; i++) {
                // On clone
                $fieldsForm.clone().append($fieldsForm);
            }
        }
    } catch (err) {
        console.log("La valeur n'est pas un entier. Réessayez.");
        console.log("Erreur relevée : " + err.message);
    }

});