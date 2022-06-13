package fr.polyflix.certification.application.http.port.output

data class PaginatedResponse<T>(val data: List<T>, val count: Int, val page: Int, val pageCount: Int, val total: Int) {
    constructor(items: List<T>) : this(items, items.size, 1, items.size, items.size)
    constructor(items: List<T>, page: Int, pageCount: Int, total: Int) : this(items, items.size, page, pageCount, total)
}
