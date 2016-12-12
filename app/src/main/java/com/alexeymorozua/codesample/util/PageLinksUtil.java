package com.alexeymorozua.codesample.util;

/**
 * Created by john on 12.12.2016.
 */

public final class PageLinksUtil {

  private static final String DELIM_LINKS = ",";
  private static final String DELIM_LINK_PARAM = ";";

  private static final String META_REL = "rel";
  private static final String META_LAST = "last";

  public static int getTotalPages(String linkHeader) {

    int pages = 0;

    if (linkHeader != null) {
      String[] links = linkHeader.split(DELIM_LINKS);
      for (String link : links) {
        String[] segments = link.split(DELIM_LINK_PARAM);
        if (segments.length < 2) continue;

        String linkPart = segments[0].trim();
        if (!linkPart.startsWith("<") || !linkPart.endsWith(">")) {
          continue;
        }
        linkPart = linkPart.substring(1, linkPart.length() - 1);

        for (int i = 1; i < segments.length; i++) {
          String[] rel = segments[i].trim().split("=");
          if (rel.length < 2 || !META_REL.equals(rel[0])) continue;

          String relValue = rel[1];
          if (relValue.startsWith("\"") && relValue.endsWith("\"")) {
            relValue = relValue.substring(1, relValue.length() - 1);
          }
          if (META_LAST.equals(relValue)) {
            pages = Integer.valueOf(
                linkPart.substring(linkPart.indexOf("page=") + 5, linkPart.lastIndexOf("&")));
          }
        }
      }
    }
    return pages;
  }
}
