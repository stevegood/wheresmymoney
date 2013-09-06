/**
 * Created with IntelliJ IDEA.
 * User: steve
 * Date: 9/6/13
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */

jQuery(function($){
    var $account = $('#account'),
        $file = $('#transactionFile'),
        $uploadBtn = $('#uploadBtn');

    $account.on('change', function(){
        var validSelection = $account.val() > 0; // is the value in the select greater than zero?
        $uploadBtn.prop('disabled', true); // disable the upload button
        $file.prop('disabled', !validSelection).val(''); // enabled or disable the file field and reset the value
    });

    $file.on('change', function(){
        console.log($file.val());
        $uploadBtn.prop('disabled', !$file.val());
    });
});