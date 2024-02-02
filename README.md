# Morocco Commerce Regions Starter

This module allows you to add Morocco regions when clicking on **Import Regions** under `Commerce > Settings > Countries`.

## Data structure
Regions are stored in a JSON file `regions.json` following this structure:
```
[
  ...
  {
    "priority": 1,
    "code":"01",
    "name":"Tanger-Tétouan-Al Hoceïma"
  },
  ...
]
```
> There's also a file named `regions_ar.json`. If you want to import the regions with their Arabic names, you can change the filename [here](modules/morocco-commerce-regions-starter/src/main/java/com/github/lgdd/liferay/commerce/morroco/regions/starter/MoroccoCommerceRegionsStarter.java#26) before compiling the module.

Each JSON object has 3 attributes:

- __priority__: order in which the region is listed
- __code__: region's code based on ISO 3166 code without `MA-` prefix
- __name__: region's name

## Requirements

- Liferay 7.4

## Resources

ISO 3166-2:MA - [wikipedia.org](https://en.wikipedia.org/wiki/ISO_3166-2:MA), [iso.org](https://www.iso.org/obp/ui/en/#iso:code:3166:MA)

## License

[MIT](LICENSE)