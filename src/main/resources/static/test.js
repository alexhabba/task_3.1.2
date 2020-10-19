$(function () {
    $('#addNewUser').on('click', function (event) {
        $('#firstname').val('');
        $('#lastname').val('');
        $('#age').val('');
        $('#email').val();
        $('#password').attr('placeholder','Enter passport');
        $('#buttonEditOrCreatNewUser').html('Add new User')
        $('#labelAddnewUserOrEditPage').html('Add new User page')
        $('#formPasswordRepeat').remove();
        $('#newAndEditUserModal').modal();
    });

    $('.table .eBtn').click(function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('#firstname').val(user.firstname);
            $('#lastname').val(user.lastname);
            $('#age').val(user.age);
            $('#email').val(user.email);
            $('#password').removeAttr('placeholder');
            $('#formPasswordRepeat').remove();
            $('#buttonEditOrCreatNewUser').html('Edit User')
            $('#labelAddnewUserOrEditPage').html('Edit User page')

        });

        $('#newAndEditUserModal').modal();
    });
});

