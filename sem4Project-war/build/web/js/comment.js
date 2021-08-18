$(document).on('click', '.btn-cmt', function (e) {
    e.preventDefault();
    var id = $("#songID").val();
    var msg = $("#msgCmt").val();
    if (!msg.trim()) {
        Swal.fire(
                'Empty message?',
                'Please enter message before enter !',
                'error'
                );
        return false;
    }
    $(".modal-comment").css('opacity', '0.5');
    $.ajax({
        url: $("#url").val() + "/player?do=comment",
        type: 'get',
        dataType: 'text',
        cache: false,
        data: {
            id: id,
            msg: msg
        },
        success: function (result) {
            console.log(result);
            if (result != null) {
               $(".comment-content").prepend(result);
            }
            $(".modal-comment").css('opacity', '1');
            $("#msgCmt").val("");
        },
        error: function () {
            alert("Error........");
        }
    });
});