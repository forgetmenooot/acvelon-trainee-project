function Validation() {
}

Validation.validName = function (name) {
    var error = "";
    if (!name) {
        $('#film-name').addClass('border-error');
        error += "Name can't be empty! ";
    } else {
        if (name.length > 45) {
            $('#film-name').addClass('border-error');
            error += "Name can't be more than 45 symbols length! ";
        } else {
            $('#film-name').addClass('border-success');
        }
    }
    return error;
};

Validation.validGenre = function (genre) {
    var error = "";
    if (!genre) {
        $('#film-genre').addClass('border-error');
        error += "Genre can't be empty! ";
    } else {
        if (genre.length > 45) {
            $('#film-genre').addClass('border-error');
            error += "Genre can't be more than 45 symbols length! ";
        } else {
            $('#film-genre').addClass('border-success');
        }
    }
    return error;
};

Validation.validYear = function (year) {
    var error = "";
    if (Math.floor(year) != year || !$.isNumeric(year) || parseInt(year) < 1901 || parseInt(year, 10) > 2015) {
        $('#film-year').addClass('border-error');
        error += "Year must be a number from 1901 to 2015! ";
    } else {
        $('#film-year').addClass('border-success');
    }
    return error;
};

Validation.validMark = function (mark) {
    var error = "";
    if (Math.floor(mark) != mark || !$.isNumeric(mark) || parseInt(mark) < 1 || parseInt(mark, 10) > 10) {
        $('#film-mark').addClass('border-error');
        error += "Mark must be a number from 1 to 10! ";
    } else {
        $('#film-mark').addClass('border-success');
    }
    return error;
};

Validation.validDateSeen = function (date_seen) {
    var error = "";
    if (date_seen != -1) {
        if (!date_seen) {
            $('#film-date-seen').addClass('border-error');
            error += "Date can't be empty! ";
        } else {
            $('#film-date-seen').addClass('border-success');
        }
    }
    return error;
};

Validation.validReview = function (review) {
    var error = "";
    if (review.length > 400) {
        $('#film-review').addClass('border-error');
        error += "Review can't be more than 400 symbols length! ";
    } else {
        $('#film-review').addClass('border-success');
    }
    return error;
};
