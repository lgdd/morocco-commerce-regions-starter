package com.github.lgdd.liferay.commerce.morroco.regions.starter;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CountryLocalService;
import com.liferay.portal.kernel.service.RegionLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;

@Component(
	immediate = true,
	property = "commerce.region.starter.key=" + MoroccoCommerceRegionsStarter.MOROCCO_NUMERIC_ISO_CODE,
	service = CommerceRegionsStarter.class)
public class MoroccoCommerceRegionsStarter implements CommerceRegionsStarter {

	public static final String FILENAME = "regions.json";
	public static final int MOROCCO_NUMERIC_ISO_CODE = 504;

	@Override
	public String getKey() {
		return String.valueOf(MOROCCO_NUMERIC_ISO_CODE);
	}

	@Override
	public String getLabel(Locale locale) {
		return "Morroco Regions Starter";
	}

	@Override
	public void start(long userId) throws Exception {
		User user = userLocalService.getUser(userId);

		Country country = _countryLocalService.fetchCountryByNumber(
				user.getCompanyId(), String.valueOf(MOROCCO_NUMERIC_ISO_CODE));

		if (country == null) {
			return;
		}

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(userId);

		ClassLoader classLoader = MoroccoCommerceRegionsStarter.class.getClassLoader();
		String json = StringUtil.read(classLoader, _STARTER_RESOURCES_PATH + FILENAME);
		JSONArray regions = _jsonFactory.createJSONArray(json);

		for (int i = 0; i < regions.length(); i++) {
			JSONObject region = regions.getJSONObject(i);
			double priority = region.getDouble("priority");
			String code = region.getString("code");
			String name = region.getString("name");
			_regionLocalService.addRegion(country.getCountryId(), true, name, priority, code, serviceContext);
		}

	}

	private static final String _STARTER_RESOURCES_PATH = "com/github/lgdd/liferay/commerce/morocco/regions/starter/internal/";

	@Reference
	private CountryLocalService _countryLocalService;

	@Reference
	private RegionLocalService _regionLocalService;

	@Reference
	protected UserLocalService userLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}