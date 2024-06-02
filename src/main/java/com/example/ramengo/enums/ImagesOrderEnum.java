package com.example.ramengo.enums;

public enum ImagesOrderEnum {
    SHOYU("https://s3-alpha-sig.figma.com/img/6401/ff3e/3cc8424496dc89e457ea0443e5b5ba22?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=f8FUcgIe5YLHvwvbjOQWZEkZnQyxJLmNTnZiQqztuIijtewMekWionUK49CodCS~y8x8O59ELFjbOWKGxpmqr-y~J59Q74c-PsrLBR-wbLJA3jjodXpwKWL4hpLV0Juoiy90h4-XEop6vlZ25AHuuYAbEGFpWCWJZX8FytJfEI6ulCax~K5kC3lcxycCrbpP4~rVLdvxUaNoO2c2LPKQLDCKfTZPjGj6PtmCOJkRHU2X0uQSHXlGbuei2RpGSlgsh6KSzE1tFU~6CARcJzItiQW6MSSiVO4IiwwuW~5Hu4XlLizRAjt3POW9lvNTGs6eX8G73KNt9XPRn0BxjQC~Nw__"),
    MISO("https://s3-alpha-sig.figma.com/img/c59f/7287/d5504de4d1174f4612db215b2074f723?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=DtBDpN4kSbn95iDN1nKEmJVlmLSOh7ypAVSzk4nIKyX3hMXtlcKfq10DHcsDRuns8B9S1615RXMPMOLBGTnJRFNjvQyDy~gdN3yTjbtWPJtv8KnjsUwxM8G9EOPjfWURZfxabH09GH5koGSFcj-rY7NLGtkKmLTq610nP9z4QDihEgWDNwuzM4HboxT~3H5HwNRL4C70sQKhKPabtOCJsyINDFbHaA8gl0-5opOacYfRvrZsDf9YxFJxdI-~0uL5i1HnMtZKFikKdGx4ejE~peuodTWrvDXS9bH~L-489TSZ~DXO0B8bS0Aq3f69jFFhVNN7imgttD3LC-kYO9jAwA__"),
    SALT("https://s3-alpha-sig.figma.com/img/3ae2/df67/13696c1c0b4f6cd8aedd12e24d921723?Expires=1717977600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=LQzlEMdgI4YVhXXti9MtPwc3kHaZ7OxEsKApfqYF5PoNxIf-vJOhMuy4j~34k3RCZo77lEVhiY7lFw7W93-Amed329bryHtDJX~8h16o-7h2TSssqQzHGlU22Ocpuj2oL09EE8tSDkTleC8t124kufxjdIueMMnXvlmTGGv29f5fYK8FvVKq3de6k2A3uNY0YATNbbYw7DGWgG4uy9WDl6mmLMXvkipzfob1NopmAOxalP2z5fh0YyyBCOwHANt5GpNaaS~bL~~XRoxhp12zr7hOmohj5Ur1tUgY81ZT-lVMOqfx~Q0vBPsjJVjl6AUCCiW7DL-fT2JDHsjcnaT9tA__");

    private final String imageUrl;

    ImagesOrderEnum(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return imageUrl;
    }
}
