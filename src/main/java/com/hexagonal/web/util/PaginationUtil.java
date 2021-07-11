package com.hexagonal.web.util;

import com.hexagonal.adapters.api.model.PageMetadataDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">GitHub
 * API</a>, and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 */
public final class PaginationUtil {

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_SIZE = 20;

  private PaginationUtil() {
  }

  private static String generateUri(String baseUrl, int page, int size) {
    return UriComponentsBuilder
        .fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
  }

  public static PageMetadataDto getPageMetadata(Page page) {
    PageMetadataDto pageMetadata = new PageMetadataDto();
    pageMetadata.setSize(page.getSize());
    pageMetadata.setNumber(page.getNumber() + 1);
    pageMetadata.setTotalElements(page.getTotalElements());
    pageMetadata.setTotalPages(page.getTotalPages());

    return pageMetadata;
  }

  public static PageRequest getPageRequest(int size, int page) {
    return PageRequest.of(getPage(page), getSize(size));
  }

  private static int getPage(final int page) {
    return page <= 0 ? DEFAULT_PAGE : page - 1;
  }

  private static int getSize(final int size) {
    return size < 0 ? DEFAULT_SIZE : size;
  }
}
