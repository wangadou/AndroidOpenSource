package com.wwb.library.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by wwb on 2017/5/11.
 */

public class DDFragmentManager
{
    private FragmentManager mManager;
    private Fragment mFragmentLastToggle;

    public DDFragmentManager(FragmentManager manager)
    {
        this.mManager = manager;
    }

    public FragmentManager getmManager()
    {
        return mManager;
    }

    public Fragment getmFragmentLastToggle()
    {
        return mFragmentLastToggle;
    }

    public FragmentTransaction beginTransaction()
    {
        return mManager.beginTransaction();
    }

    public Fragment findFragmentByTag(String tag)
    {
        return mManager.findFragmentByTag(tag);
    }

    public Fragment findFragmentById(int id)
    {
        return mManager.findFragmentById(id);
    }

    /**
     * @date 2017/5/13
     * @author wwb
     * @Description 移除多个fragment
     */
    public DDFragmentManager remove(Fragment... fragments)
    {
        if (fragments != null && fragments.length > 0)
        {
            FragmentTransaction transaction = beginTransaction();
            Fragment fragment = null;
            for (int i = 0; i < fragments.length; i++)
            {
                fragment = fragments[i];
                if (fragment != null)
                {
                    transaction.remove(fragment);
                }
            }
            transaction.commitAllowingStateLoss();
        }
        return this;
    }

    public DDFragmentManager show(Fragment... fragments)
    {
        if (fragments != null && fragments.length > 0)
        {
            FragmentTransaction transaction = beginTransaction();
            Fragment fragment = null;
            for (int i = 0; i < fragments.length; i++)
            {
                fragment = fragments[i];
                if (fragment != null)
                {
                    transaction.show(fragment);
                }
            }
            transaction.commitAllowingStateLoss();
        }
        return this;
    }

    public DDFragmentManager hide(Fragment... fragments)
    {
        if (fragments != null && fragments.length > 0)
        {
            FragmentTransaction transaction = beginTransaction();
            Fragment fragment = null;
            for (int i = 0; i < fragments.length; i++)
            {
                fragment = fragments[i];
                if (fragment != null)
                {
                    transaction.hide(fragment);
                }
            }
            transaction.commitAllowingStateLoss();
        }
        return this;
    }

    public Fragment replace(int container, Fragment fragment)
    {
        return replace(container, fragment, null, false);
    }

    public Fragment replace(int container, Fragment fragment, Bundle args)
    {
        return replace(container, fragment, args, false);
    }

    public Fragment replace(int container, Class<? extends Fragment> fragmentClazz)
    {
        return replace(container, fragmentClazz, null, false);
    }

    public Fragment replace(int container, Class<? extends Fragment> fragmentClazz, Bundle args)
    {
        return replace(container, fragmentClazz, args, false);
    }

    public Fragment replace(int container, Class<? extends Fragment> fragmentClazz, Bundle args, boolean addToBackStack)
    {
        return replace(container, newFragment(fragmentClazz), args, addToBackStack);
    }

    public Fragment replace(int container, Fragment fragment, Bundle args, boolean addToBackStack)
    {
        if (fragment != null)
        {
            putFragmentData(fragment, args);
            FragmentTransaction transaction = beginTransaction();
            String tag = fragment.getClass().getSimpleName();

            transaction.replace(container, fragment, tag);
            if (addToBackStack)
            {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
        }
        return fragment;
    }


    public Fragment add(int container, Fragment fragment)
    {
        return add(container, fragment, null, false);
    }

    public Fragment add(int container, Fragment fragment, Bundle args)
    {
        return add(container, fragment, args, false);
    }

    public Fragment add(int container, Class<? extends Fragment> fragmentClazz)
    {
        return add(container, fragmentClazz, null, false);
    }

    public Fragment add(int container, Class<? extends Fragment> fragmentClazz, Bundle args)
    {
        return add(container, fragmentClazz, args, false);
    }

    public Fragment add(int container, Class<? extends Fragment> fragmentClazz, Bundle args, boolean addToBackStack)
    {
        return add(container, newFragment(fragmentClazz), args, addToBackStack);
    }

    public Fragment add(int container, Fragment fragment, Bundle args, boolean addToBackStack)
    {
        if (fragment != null)
        {
            putFragmentData(fragment, args);
            FragmentTransaction transaction = beginTransaction();
            String tag = fragment.getClass().getSimpleName();

            transaction.add(container, fragment, tag);
            if (addToBackStack)
            {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
        }
        return fragment;
    }


    public Fragment toggle(int container, Fragment hideFragment, Class<? extends Fragment> showFragmentClazz)
    {
        return toggle(container, hideFragment, showFragmentClazz, null, false);
    }

    public Fragment toggle(int container, Fragment hideFragment, Class<? extends Fragment> showFragmentClazz, Bundle args)
    {
        return toggle(container, hideFragment, showFragmentClazz, args, false);
    }

    public Fragment toggle(int container, Fragment hideFragment, Class<? extends Fragment> showFragmentClazz, Bundle args, boolean addToBackStack)
    {
        final String tag = showFragmentClazz.getSimpleName();
        Fragment fragment = findFragmentByTag(tag);

        if (fragment != null)
        {
            fragment = toggle(container, hideFragment, fragment, args, addToBackStack);
        } else
        {
            fragment = newFragment(showFragmentClazz);
            if (fragment != null)
            {
                FragmentTransaction transaction = beginTransaction();
                putFragmentData(fragment, args);
                if (hideFragment == null)
                {
                    hideFragment = mFragmentLastToggle;
                }
                if (hideFragment != null)
                {
                    transaction.hide(hideFragment);
                }
                transaction.add(container, fragment, tag);
                if (addToBackStack)
                {
                    transaction.addToBackStack(null);
                }
                transaction.commitAllowingStateLoss();
            }
        }
        mFragmentLastToggle = fragment;
        return fragment;
    }

    public Fragment toggle(Fragment hideFragment, Fragment showFragment)
    {
        return toggle(0, hideFragment, showFragment, null, false);
    }

    public Fragment toggle(Fragment hideFragment, Fragment showFragment, Bundle args)
    {
        return toggle(0, hideFragment, showFragment, args, false);
    }

    public Fragment toggle(int container, Fragment hideFragment, Fragment showFragment)
    {
        return toggle(container, hideFragment, showFragment, null, false);
    }

    public Fragment toggle(int container, Fragment hideFragment, Fragment showFragment, Bundle args)
    {
        return toggle(container, hideFragment, showFragment, args, false);
    }

    /**
     * @Description 切换两个fragment
     */
    public Fragment toggle(int container, Fragment hideFragment, Fragment showFragment, Bundle args, boolean addToBackStack)
    {
        if (showFragment != null)
        {
            final FragmentTransaction transaction = beginTransaction();
            final String tag = showFragment.getClass().getSimpleName();
            putFragmentData(showFragment, args);
            if (showFragment != hideFragment)
            {
                if (hideFragment == null)
                {
                    hideFragment = mFragmentLastToggle;
                }
                if (hideFragment != null)
                {
                    transaction.hide(hideFragment);
                }

                if (!showFragment.isAdded() && container != 0)
                {
                    transaction.add(container, showFragment, tag);
                }
                transaction.show(showFragment);

                if (addToBackStack)
                {
                    transaction.addToBackStack(null);
                }
                transaction.commitAllowingStateLoss();
            }
        }
        mFragmentLastToggle = showFragment;
        return showFragment;
    }


    public static void putFragmentData(Fragment fragment, Bundle args)
    {
        if (fragment != null)
        {
            if (args != null && !args.isEmpty())
            {
                final Bundle bundle = fragment.getArguments();
                if (bundle != null)
                {
                    bundle.putAll(args);
                } else
                {
                    fragment.setArguments(args);
                }
            }
        }
    }

    public static Fragment newFragment(Class<? extends Fragment> clazz)
    {
        Fragment fragment = null;
        try
        {
            fragment = clazz.newInstance();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return fragment;
    }

}
