var feelingID = 1;
var idSearch = 0;

/// new post
$(".tooltip").on('click', function (e) {
    e.preventDefault();
    var toggle = e.target.dataset.toggle;
    console.log(toggle);
    var content = document.querySelector(`#${toggle}`);
    content.classList.add("open");
    $("#msgStory").val("");
    idSearch = 0;
    $(".btn-search-song").find("span").html("Choose a song that expresses your mood");
});

// submit create post
$("#frmStory").on('submit', function () {
    $.ajax({
        url: $("#url").val() + "/ClientStory",
        type: 'post',
        cache: false,
        dataType: 'text',
        data: {
            submit: 'create',
            msg: $("#msgStory").val(),
            feeling: feelingID,
            song: idSearch
        },
        success: function (result) {
            $("#new-post").removeClass("open");
            $(".profile-diary-list").prepend(result);
        },
        error: function () {
            alert("Occur error when request");
        }
    });
    return false;
});

$(".feeling-item").hover(function () {
    $(this).toggleClass('bx-tada');
});

$(".feeling-item").on('click', function () {
    $(".feeling-item").removeClass("active");
    $(this).addClass('active');
    feelingID = $(this).attr("data-id");
    console.log("feeling " + feelingID);

});


////// search song to post
$('.btn-search-song').on('click', function (e) {
    e.preventDefault();
    $(".theme-choose-song").addClass('open');
    $('#keyup').val("");
    $('.form-result-table').html("");
});
$(document).on('click', ".form-result-table tr", function (e) {
    var row = $(this).attr("id");
    var text = $(this).find(".title").text();
    $(".btn-search-song").find("span").html("Share this post along with " + text);
    $(".form-result-table tr").find(".form-check").removeClass("check");
    $(this).find(".form-check").toggleClass("check");
    idSearch = row;
    console.log(row);
});
$(".form-choose-song-end .zm-btn-close").on('click', function (e) {
    $(".theme-choose-song").removeClass('open');
});

// Delay the keyup event for jquery ajax
$(document).ready(function ()
{
    // Khai báo đối tượng timeout để dùng cho hàm
    // clearTimeout
    var timeout = null;

    // Sự kiện keyup
    $('#keyup').keyup(function ()
    {
        // Xóa đi những gì ta đã thiết lập ở sự kiện 
        // keyup của ký tự trước (nếu có)
        clearTimeout(timeout);

        // Sau khi xóa thì thiết lập lại timeout
        timeout = setTimeout(function ()
        {
            // Lấy nội dung search
            var data = {
                name: $('#keyup').val()
            };

            // Gửi ajax search
            $.ajax({
                type: 'get',
                dataType: 'text',
                data: data,
                url: $("#url").val() + "/ClientStory?do=song",
                success: function (result) {
                    $('.form-result-table').html(result);
                },
                error: function () {
                    alert("error");
                }
            });
        }, 1000);
    });
});

/// block user
$(".bg-red").on('click', function (e) {
    e.preventDefault();
    var id = $(this).attr("data-id");
    console.log(id);
    Swal.fire({
        title: 'Are you sure?',
        text: "This account is going block and cannot login any more!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, I know!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: $("#url").val() + "/admin-account?do=block",
                type: 'get',
                dataType: 'json',
                cache: false,
                data: {
                    id: id
                },
                success: function (result) {
                    if (result.msg == "block") {
                        Swal.fire(
                                'Success!',
                                'Account has blocked!',
                                'success'
                                );
                    } else {
                        Swal.fire(
                                'Success!',
                                'Account has unblocked!',
                                'success'
                                );
                    }
                },
                error: function () {
                    Swal.fire(
                            'Error!',
                            'Can not handling this request right now!',
                            'error'
                            );
                }
            });
        }
    })
});

/// block user
$(".bg-yellow").on('click', function (e) {
    e.preventDefault();
    var id = $(this).attr("data-id");
    console.log(id);
    Swal.fire({
        title: 'Are you sure?',
        text: "This account is going unblock!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, I know!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: $("#url").val() + "/admin-account?do=block",
                type: 'get',
                dataType: 'json',
                cache: false,
                data: {
                    id: id
                },
                success: function (result) {
                    if (result.msg == "block") {
                        Swal.fire(
                                'Success!',
                                'Account has blocked!',
                                'success'
                                );
                    } else {
                        Swal.fire(
                                'Success!',
                                'Account has unblocked!',
                                'success'
                                );
                    }
                },
                error: function () {
                    Swal.fire(
                            'Error!',
                            'Can not handling this request right now!',
                            'error'
                            );
                }
            });
        }
    })
});


function previewFile(input) {
    var file = $("input[type=file]").get(0).files[0];
    var preThum = document.querySelector('.avatar-click');
    if (file) {
        var reader = new FileReader();
        reader.onload = function () {
            preThum.src = reader.result;
        }
        reader.readAsDataURL(file);
    }
}

$('#formSetting').submit(function () {
    var formData = new FormData(this);
    formData.append("submit", "setting");
    $.ajax({
        url: $("#url").val() + "/clientAccount",
        type: 'post',
        dataType: 'json',
        contentType: false,
        processData: false,
        data: formData,
        success: function (result) {
            if (result.msg == "success") {
                $("#setting").removeClass("open");
                Swal.fire(
                        'Success!',
                        'New information has been updating',
                        'success'
                        );
            } else if (result.msg == "file") {
                Swal.fire(
                        'Error!',
                        'File uploaded is not a valid format. You must have uploaded file with extension JPG, PNG, JPEG!',
                        'error'
                        );
            }
        },
        error: function () {
            Swal.fire(
                    'Error!',
                    'Occur error when update new profile!',
                    'error'
                    );
        }
    });
    return false;
});

// change password
$("#formChange").submit(function (e) {
    if ($(this).find("input[name='oldpass']").val().length < 1) {
        Swal.fire('Error!', 'Please, enter your old password!', 'error');
    } else if ($(this).find("input[name='newpass']").val().length < 5) {
        Swal.fire('Error!', 'New password must be over 5 and less than 12 characters in length!', 'error');
    } else {
        $.ajax({
            url: $("#url").val() + "/clientAccount",
            type: 'post',
            dataType: 'json',
            cache: false,
            data: {
                submit: 'changepass',
                oldpass: $("#oldpass").val(),
                newpass: $("#newpass").val()
            },
            success: function (result) {
                console.log(result);
                if(result.msg == "no"){
                    Swal.fire('Error!', 'Your old password is not correct, try again!', 'error');
                }
                if(result.msg == "length"){
                    Swal.fire('Error!', 'New password must be over 5 and less than 12 characters in length!!', 'error');
                }
                if(result.msg == "yes"){
                    $("#change").removeClass("open");
                    Swal.fire('Success!', 'Change password success!', 'success');
                }
            },
            error: function () {
                alert("Occur error when handling this request.");
            }
        });
    }
    return false;
});